package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Industry;


@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long>{

	Industry findByDescription(String description);
	
}
