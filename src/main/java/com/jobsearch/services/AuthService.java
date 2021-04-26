package com.jobsearch.services;

import org.springframework.http.ResponseEntity;

import com.jobsearch.data.vo.UserVO;
import com.jobsearch.security.AccountCredentialsVO;

public interface AuthService {
	public ResponseEntity<?> signin(AccountCredentialsVO data);

	public ResponseEntity<Boolean> registerUser(AccountCredentialsVO data);

	public UserVO confirmUserAccount(String verificationToken);
	
	public UserVO findUserById(Long id);
}
