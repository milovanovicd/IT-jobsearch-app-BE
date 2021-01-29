package com.jobsearch.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author dejanmilovanovic
 *
 */
@Embeddable
public class JobApplicationId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "job_id")
	private long jobId;

	@Column(name = "candidate_id")
	private long candidateId;

	public JobApplicationId() {
	}

	public JobApplicationId(long jobId, long candidateId) {
		this.jobId = jobId;
		this.candidateId = candidateId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (candidateId ^ (candidateId >>> 32));
		result = prime * result + (int) (jobId ^ (jobId >>> 32));
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
		JobApplicationId other = (JobApplicationId) obj;
		if (candidateId != other.candidateId)
			return false;
		if (jobId != other.jobId)
			return false;
		return true;
	}

}
