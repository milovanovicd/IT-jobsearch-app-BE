package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Position;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{

	Position findByDescription(String description);
	
}
