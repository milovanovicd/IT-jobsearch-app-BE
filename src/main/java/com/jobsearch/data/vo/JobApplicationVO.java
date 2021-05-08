package com.jobsearch.data.vo;

import java.io.Serializable;

/**
 * @author dejanmilovanovic
 *
 */
public class JobApplicationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private JobApplicationJobVO job;
	private JobApplicationCandidateVO candidate;
	private String createdOn;

	public JobApplicationVO() {
		// TODO Auto-generated constructor stub
	}

	public JobApplicationVO(JobApplicationJobVO job, JobApplicationCandidateVO candidate, String createdOn) {
		this.job = job;
		this.candidate = candidate;
		this.createdOn = createdOn;
	}

	public JobApplicationJobVO getJob() {
		return job;
	}

	public void setJob(JobApplicationJobVO job) {
		this.job = job;
	}

	public JobApplicationCandidateVO getCandidate() {
		return candidate;
	}

	public void setCandidate(JobApplicationCandidateVO candidate) {
		this.candidate = candidate;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidate == null) ? 0 : candidate.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
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
		JobApplicationVO other = (JobApplicationVO) obj;
		if (candidate == null) {
			if (other.candidate != null)
				return false;
		} else if (!candidate.equals(other.candidate))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		return true;
	}

}
