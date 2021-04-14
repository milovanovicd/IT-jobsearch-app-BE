package com.jobsearch.data.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author dejanmilovanovic
 *
 */
public class MetadataVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<IndustryVO> industries;
	private List<PositionVO> positions;
	private List<TechnologyVO> technologies;

	public List<IndustryVO> getIndustries() {
		return industries;
	}

	public void setIndustries(List<IndustryVO> industries) {
		this.industries = industries;
	}

	public List<PositionVO> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionVO> positions) {
		this.positions = positions;
	}

	public List<TechnologyVO> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<TechnologyVO> technologies) {
		this.technologies = technologies;
	}

}
