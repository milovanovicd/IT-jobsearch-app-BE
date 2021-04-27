package com.jobsearch.services.impl;

import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Candidate;
import com.jobsearch.data.model.Company;
import com.jobsearch.data.model.Permission;
import com.jobsearch.data.model.User;
import com.jobsearch.data.model.VerificationToken;
import com.jobsearch.data.vo.UserVO;
import com.jobsearch.repository.CandidateRepository;
import com.jobsearch.repository.CompanyRepository;
import com.jobsearch.repository.PermissionRepository;
import com.jobsearch.repository.UserRepository;
import com.jobsearch.repository.VerificationTokenRepository;
import com.jobsearch.security.AccountCredentialsVO;
import com.jobsearch.security.jwt.JwtTokenProvider;
import com.jobsearch.services.AuthService;
import com.jobsearch.services.EmailSenderService;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	EmailSenderService emailSenderService;

	@Override
	public ResponseEntity<?> signin(AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = userRepository.findByUsername(username);
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

	@Override
	public ResponseEntity<Boolean> registerUser(AccountCredentialsVO data) {
		User existingUser = userRepository.findByUsername(data.getUsername());

		if (existingUser != null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		} else {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
			String encodedPassword = encoder.encode(data.getPassword());

			User newUser = new User();

			newUser.setUsername(data.getUsername());
			newUser.setPassword(encodedPassword);
			newUser.setAccountNonExpired(true);
			newUser.setAccountNonLocked(true);
			newUser.setCredentialsNonExpired(true);
			
			List<Permission> permissionsList = new ArrayList<Permission>();

			if (data.getAccountType().equalsIgnoreCase("company")) {
				Company company = new Company();
				company.setUser(newUser);
				newUser.setCompany(company);
				permissionsList.add(permissionRepository.findByDescription("COMPANY"));
				newUser.setPermissions(permissionsList);
			} else {
				Candidate candidate = new Candidate();
				candidate.setUser(newUser);
				newUser.setCandidate(candidate);
				permissionsList.add(permissionRepository.findByDescription("CANDIDATE"));
				newUser.setPermissions(permissionsList);
			}

			userRepository.save(newUser);

			VerificationToken verificationToken = new VerificationToken(newUser);
			verificationTokenRepository.save(verificationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(newUser.getUsername());
			mailMessage.setSubject("Account Registration Verification!");
			mailMessage.setFrom("office@jobsearch.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:4200/confirm-account?token=" + verificationToken.getVerificationToken());

			emailSenderService.sendEmail(mailMessage);

			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}

	@Override
	public UserVO confirmUserAccount(String verificationToken) {

		try {
			VerificationToken token = verificationTokenRepository.findByVerificationToken(verificationToken);

			if (token != null) {
				Calendar cal = Calendar.getInstance();

				if ((token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

					verificationTokenRepository.delete(token);

					User user = userRepository.findByUsername(token.getUser().getUsername());

					if (user.getCompany() != null) {
						user.setCompany(null);
						companyRepository.delete(user.getCompany());
					} else if (user.getCandidate() != null) {
						user.setCandidate(null);
						candidateRepository.delete(user.getCandidate());
					}

					userRepository.delete(user);

					throw new ResourceNotFoundException("Token expired!");
				}

				User user = userRepository.findByUsername(token.getUser().getUsername());

				user.setEnabled(true);
				userRepository.save(user);
				verificationTokenRepository.delete(token);

				return DozerConverter.parseObject(user, UserVO.class);

			} else {
				throw new ResourceNotFoundException("Token invalid...");
			}

		} catch (Exception e) {
			throw new ResourceNotFoundException("ERROR");
		}
	}

	@Override
	public UserVO findUserById(Long id) {
		var user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		return DozerConverter.parseObject(user, UserVO.class);
	}

}
