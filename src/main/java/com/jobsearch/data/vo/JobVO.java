	package com.jobsearch.data.vo;

import java.io.Serializable;
import java.util.List;

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

	@Override
	public String toString() {
		return "JobVO [name=" + name + ", description=" + description + ", publishedDate=" + publishedDate
				+ ", deadlineDate=" + deadlineDate + ", position=" + position + ", seniority=" + seniority
				+ ", companyId=" + companyId + ", technologies=" + technologies + "]";
	}
	
	
}
