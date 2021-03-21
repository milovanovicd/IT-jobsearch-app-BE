package com.jobsearch.data.vo;

import java.io.Serializable;

/**
 * @author dejanmilovanovic
 *
 */
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String username;
	private String fullName;
	private String password;
	
//	private Candidate candidate;



	public String getPassword() {
		return this.password;
	}

	
	public String getUsername() {
		return this.username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
