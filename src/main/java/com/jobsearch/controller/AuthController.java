package com.jobsearch.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Candidate;
import com.jobsearch.data.model.Company;
import com.jobsearch.data.model.User;
import com.jobsearch.data.model.VerificationToken;
import com.jobsearch.data.vo.UserVO;
import com.jobsearch.repository.UserRepository;
import com.jobsearch.repository.VerificationTokenRepository;
import com.jobsearch.security.AccountCredentialsVO;
import com.jobsearch.security.jwt.JwtTokenProvider;
import com.jobsearch.services.EmailSenderService;
import com.jobsearch.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Authorization Endpoint", tags = "AuthorizationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserRepository repository;

	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailSenderService emailSenderService;

	@ApiOperation(value = "Authenticate a user and return a token")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = repository.findByUsername(username);
			var userVo = DozerConverter.parseObject(user, UserVO.class);

			var token = "";

			if (user != null) {
				token = tokenProvider.createToken(userVo, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " Not Found!");
			}

			Map<Object, Object> model = new HashMap<>();

			model.put("username", username);
			model.put("token", token);

			return ok(model);

		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser(@RequestBody AccountCredentialsVO data) {
		User existingUser = repository.findByUsername(data.getUsername());

		if(existingUser != null){
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		} else {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(data.getPassword());
			
			User newUser = new User();
			
			newUser.setUsername(data.getUsername());
			newUser.setPassword(encodedPassword);
			
			if(data.getAccountType() == "company") {
				Company company = new Company();
				company.setUser(newUser);
				newUser.setCompany(company);
			}else {
				Candidate candidate = new Candidate();
				candidate.setUser(newUser);
				newUser.setCandidate(candidate);
			}

			repository.save(newUser);

			VerificationToken verificationToken = new VerificationToken(newUser);
			verificationTokenRepository.save(verificationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(newUser.getUsername());
			mailMessage.setSubject("Account Registration Verification!");
			mailMessage.setFrom("office@jobsearch.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/auth/confirm-account?token=" + verificationToken.getVerificationToken());
			// Redirektuj na frontend pa onda posalji get ka backendu

			emailSenderService.sendEmail(mailMessage);


			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

	}

//    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	@GetMapping("/confirm-account")
	public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String verificationToken) {
		
		VerificationToken token = verificationTokenRepository.findByVerificationToken(verificationToken);

		if (token != null) {
			Calendar cal = Calendar.getInstance();
			
			if ((token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

				verificationTokenRepository.delete(token);
				User user = repository.findByUsername(token.getUser().getUsername());
				repository.delete(user);

				return new ResponseEntity<>("Token has expired", HttpStatus.BAD_REQUEST);
			}


			User user = repository.findByUsername(token.getUser().getUsername());

			System.out.println(user.getUsername());
			user.setEnabled(true);
			repository.save(user);
			
			return new ResponseEntity<>(
					"accountVerified",
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Verification Error",
					HttpStatus.BAD_REQUEST);
		}

	}
}
