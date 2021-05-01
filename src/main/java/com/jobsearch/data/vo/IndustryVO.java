package com.jobsearch.data.vo;

import java.io.Serializable;

/**
 * @author dejanmilovanovic
 *
 */
public class IndustryVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String description;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
