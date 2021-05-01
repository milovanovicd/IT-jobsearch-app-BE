package com.jobsearch.data.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * @author dejanmilovanovic
 *
 */
public class JobVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private String publishedDate;
	private String deadlineDate;
	
	@NotNull
	private String position;
	
	@NotNull
	private long seniority;
	
	@NotNull
	private long companyId;
	
	private List<String> technologies;
	private Set<JobApplicationVO> jobApplications;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public long getSeniority() {
		return seniority;
	}

	public void setSeniority(long seniority) {
		this.seniority = seniority;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public List<String> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<String> technologies) {
		this.technologies = technologies;
	}
	
	public Set<JobApplicationVO> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(Set<JobApplicationVO> jobApplications) {
		this.jobApplications = jobApplications;
	}

	@Override
	public String toString() {
		return "JobVO [name=" + name + ", description=" + description + ", publishedDate=" + publishedDate
				+ ", deadlineDate=" + deadlineDate + ", position=" + position + ", seniority=" + seniority
				+ ", companyId=" + companyId + ", technologies=" + technologies + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + ((deadlineDate == null) ? 0 : deadlineDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((publishedDate == null) ? 0 : publishedDate.hashCode());
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
		JobVO other = (JobVO) obj;
		if (companyId != other.companyId)
			return false;
		if (deadlineDate == null) {
			if (other.deadlineDate != null)
				return false;
		} else if (!deadlineDate.equals(other.deadlineDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (publishedDate == null) {
			if (other.publishedDate != null)
				return false;
		} else if (!publishedDate.equals(other.publishedDate))
			return false;
		return true;
	}
	
}
