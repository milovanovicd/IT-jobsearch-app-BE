package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Seniority;


@Repository
public interface SeniorityRepository extends JpaRepository<Seniority, Long>{

	Seniority findByDescription(String description);
	
}
