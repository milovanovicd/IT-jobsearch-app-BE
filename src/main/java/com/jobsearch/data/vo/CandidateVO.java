package com.jobsearch.data.vo;

import java.io.Serializable;
import java.util.Set;

/**
 * @author dejanmilovanovic
 *
 */
public class CandidateVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String fullName;
	private int age;
	private String address;
	private String resume;
	private Set<JobApplicationVO> jobApplications;

	public CandidateVO() {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
	
	public Set<JobApplicationVO> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(Set<JobApplicationVO> jobApplications) {
		this.jobApplications = jobApplications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		CandidateVO other = (CandidateVO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.fullName;
	}

}
