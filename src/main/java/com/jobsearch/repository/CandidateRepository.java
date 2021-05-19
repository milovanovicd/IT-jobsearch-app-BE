package com.jobsearch.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Candidate;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	@Modifying
	@Query("delete from Candidate c WHERE c.id =:id")
	@Transactional
	void deleteCandidate(@Param("id") long id);
}
