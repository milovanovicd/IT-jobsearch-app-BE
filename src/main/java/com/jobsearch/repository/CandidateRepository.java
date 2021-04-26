package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Candidate;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
