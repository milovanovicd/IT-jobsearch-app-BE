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
	private UserCompanyVO company;
	private UserCandidateVO candidate;

	public String getUsername() {
		return this.username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
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
