package com.jobsearch.data.vo;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

/**
 * @author dejanmilovanovic
 *
 */
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	@NotNull
    @NotEmpty
	private String username;
	
	@NotNull
    @NotEmpty
	private String password;
	private UserCompanyVO company;
	private UserCandidateVO candidate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserCompanyVO getCompany() {
		return company;
	}

	public void setCompany(UserCompanyVO company) {
		this.company = company;
	}

	public UserCandidateVO getCandidate() {
		return candidate;
	}

	public void setCandidate(UserCandidateVO candidate) {
		this.candidate = candidate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

}
