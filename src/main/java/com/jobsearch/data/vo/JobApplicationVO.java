package com.jobsearch.data.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dejanmilovanovic
 *
 */
public class JobApplicationVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private JobVO job;
	private CandidateVO candidate;
	private Date createdOn;
	
	public JobApplicationVO() {
		// TODO Auto-generated constructor stub
	}
	
	public JobApplicationVO(JobVO job, CandidateVO candidate, Date createdOn) {
		this.job = job;
		this.candidate = candidate;
		this.createdOn = createdOn;
	}
	public JobVO getJob() {
		return job;
	}
	public void setJob(JobVO job) {
		this.job = job;
	}
	public CandidateVO getCandidate() {
		return candidate;
	}
	public void setCandidate(CandidateVO candidate) {
		this.candidate = candidate;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
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
