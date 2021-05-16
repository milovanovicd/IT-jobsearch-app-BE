package com.jobsearch.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jobsearch.data.model.Job;
import com.jobsearch.data.vo.JobGetVO;
import com.jobsearch.data.vo.JobVO;

public interface JobService {
	public List<Job> findAll(Pageable pageable);
	public List<Job> searchAll(Pageable pageable, String name, List<String> technologies, List<String> positions, List<String> seniorities);
	public Job findById(Long id);
	public JobGetVO create(JobVO job);
	public JobGetVO update(Long id, JobVO job);
	public void delete(Job job);
	public void checkExpireDates();
}
