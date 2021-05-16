package com.jobsearch.services;

import com.jobsearch.data.model.JobApplication;
import com.jobsearch.data.model.JobApplicationId;
import com.jobsearch.data.vo.JobApplicationCreateVO;
import com.jobsearch.data.vo.JobApplicationVO;

public interface JobApplicationService {
	public JobApplication findById(JobApplicationId id);
	public JobApplicationVO create(JobApplicationCreateVO jobApplication);
	public void delete(JobApplication jobApplication);
	public void deleteByJobId(long jobId);
}
