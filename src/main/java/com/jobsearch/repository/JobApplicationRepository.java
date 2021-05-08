package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.JobApplication;
import com.jobsearch.data.model.JobApplicationId;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, JobApplicationId> {}
