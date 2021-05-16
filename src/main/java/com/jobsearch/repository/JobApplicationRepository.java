package com.jobsearch.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.JobApplication;
import com.jobsearch.data.model.JobApplicationId;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, JobApplicationId> {
	
	@Modifying
	@Query("delete from JobApplication jobApplication WHERE jobApplication.job.id =:id")
	@Transactional
	void deleteByIdJobId(@Param("id") long id);
}
