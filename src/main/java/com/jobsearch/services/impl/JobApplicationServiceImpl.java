package com.jobsearch.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.JobApplication;
import com.jobsearch.data.model.JobApplicationId;
import com.jobsearch.data.vo.JobApplicationCandidateVO;
import com.jobsearch.data.vo.JobApplicationCreateVO;
import com.jobsearch.data.vo.JobApplicationJobVO;
import com.jobsearch.data.vo.JobApplicationVO;
import com.jobsearch.exception.ResourceNotFoundException;
import com.jobsearch.repository.JobApplicationRepository;
import com.jobsearch.services.CandidateService;
import com.jobsearch.services.JobApplicationService;
import com.jobsearch.services.JobService;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	JobApplicationRepository repository;

	@Autowired
	JobService jobService;

	@Autowired
	CandidateService candidateService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	@Override
	public JobApplication findById(JobApplicationId id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
	}

	@Override
	public JobApplicationVO create(JobApplicationCreateVO jobApplication) {
		JobApplication entity = new JobApplication();

		entity.setCreatedOn(new Date());
		entity.setId(new JobApplicationId(jobApplication.getJobId(), jobApplication.getCandidateId()));
		entity.setCandidate(this.candidateService.findById(jobApplication.getCandidateId()));
		entity.setJob(this.jobService.findById(jobApplication.getJobId()));

		JobApplication saved = this.repository.save(entity);
		
		return new JobApplicationVO(
				DozerConverter.parseObject(saved.getJob(), JobApplicationJobVO.class),
				DozerConverter.parseObject(saved.getCandidate(), JobApplicationCandidateVO.class), 
				sdf.format(new Date(saved.getCreatedOn().getTime())).toString());
	}

	@Override
	public void delete(JobApplication jobApplication) {
		this.repository.delete(jobApplication);
	}
	
	@Override
	public void deleteByJobId(long jobId) {
		this.repository.deleteByIdJobId(jobId);
	}
}
