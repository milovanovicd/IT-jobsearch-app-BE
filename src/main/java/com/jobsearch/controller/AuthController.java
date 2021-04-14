package com.jobsearch.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.vo.UserVO;
import com.jobsearch.repository.UserRepository;
import com.jobsearch.security.AccountCredentialsVO;
import com.jobsearch.security.jwt.JwtTokenProvider;

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
	
	@ApiOperation(value = "Signup a user and return a token")
	@PostMapping(value = "/signup")
	public ResponseEntity signup(@RequestBody AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = repository.findByUsername(username);

			var token = "";

			if (user != null) {
//				token = tokenProvider.createToken(username, user.getRoles());
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
}
