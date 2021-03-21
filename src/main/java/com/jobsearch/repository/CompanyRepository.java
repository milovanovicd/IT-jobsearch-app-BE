package com.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}
