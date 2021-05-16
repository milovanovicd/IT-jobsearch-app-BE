package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Status;


@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

	Status findByDescription(String description);
	
}
