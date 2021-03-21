package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Technology;


@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long>{

	Technology findByDescription(String description);
	
}
