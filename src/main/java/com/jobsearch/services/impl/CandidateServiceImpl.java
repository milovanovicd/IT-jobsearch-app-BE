package com.jobsearch.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Candidate;
import com.jobsearch.data.vo.CandidateVO;
import com.jobsearch.exception.ResourceNotFoundException;
import com.jobsearch.repository.CandidateRepository;
import com.jobsearch.repository.JobApplicationRepository;
import com.jobsearch.services.CandidateService;
import com.jobsearch.services.UserService;

@Service
public class CandidateServiceImpl implements CandidateService{
	
	@Autowired
	CandidateRepository repository;
	
	@Autowired
	JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	UserService userService;

	@Override
	public Candidate findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
	}

	@Override
	public CandidateVO update(Long id, CandidateVO candidate) {
		Candidate entity = this.findById(id);
		
		entity.setFullName(candidate.getFullName());
		entity.setAddress(candidate.getAddress());
		entity.setAge(candidate.getAge());


		return DozerConverter.parseObject(repository.save(entity), CandidateVO.class);
	}

	@Override
	public void delete(Long id) {
		Candidate entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		
		Long userId = entity.getUser().getId();
		
		entity.setUser(null);
		
		jobApplicationRepository.deleteByIdCandidateId(id);
		
		repository.deleteCandidate(id);
		
		userService.delete(userId);
		
	}

}
