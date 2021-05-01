package com.jobsearch.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.vo.IndustryVO;
import com.jobsearch.data.vo.MetadataVO;
import com.jobsearch.data.vo.PositionVO;
import com.jobsearch.data.vo.TechnologyVO;
import com.jobsearch.repository.IndustryRepository;
import com.jobsearch.repository.PositionRepository;
import com.jobsearch.repository.TechnologyRepository;
import com.jobsearch.services.MetadataService;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	IndustryRepository industryRepository;

	@Autowired
	PositionRepository positionRepository;

	@Autowired
	TechnologyRepository technologyRepository;

	@Override
	public MetadataVO getMetadata() {
		var metadata = new MetadataVO();
		metadata.setIndustries(DozerConverter.parseListObjects(this.industryRepository.findAll(), IndustryVO.class));
		metadata.setPositions(DozerConverter.parseListObjects(this.positionRepository.findAll(), PositionVO.class));
		metadata.setTechnologies(DozerConverter.parseListObjects(this.technologyRepository.findAll(), TechnologyVO.class));
		
		return metadata;
	}

}
