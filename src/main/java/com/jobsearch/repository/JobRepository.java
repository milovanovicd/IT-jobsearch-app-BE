package com.jobsearch.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Job;


@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
	
	@Modifying
	@Query("delete from Job job WHERE job.id =:id")
	@Transactional
	void deleteJob(@Param("id") long id);

}
