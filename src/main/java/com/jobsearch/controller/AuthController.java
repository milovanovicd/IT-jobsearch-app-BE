package com.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.data.vo.UserVO;
import com.jobsearch.security.AccountCredentialsVO;
import com.jobsearch.services.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Authorization Endpoint", tags = "AuthorizationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@ApiOperation(value = "Authenticate a user and return a token")
	@PostMapping(value = "/signin")
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO data) {
		return this.authService.signin(data);
	}

	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser(@RequestBody AccountCredentialsVO data) {
		return this.authService.registerUser(data);
	}

	@GetMapping("/confirm-account")
	public UserVO confirmUserAccount(@RequestParam("token") String verificationToken) {
		return this.authService.confirmUserAccount(verificationToken);
	}

	@ApiOperation(value = "Find a specific user by ID")
	@GetMapping("users/{id}")
	public UserVO findUserById(@PathVariable("id") Long id) {
		return this.authService.findUserById(id);
	}
}
