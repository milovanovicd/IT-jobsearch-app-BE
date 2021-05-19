package com.jobsearch.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobsearch.data.model.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	@Modifying
	@Query("delete from Company c WHERE c.id =:id")
	@Transactional
	void deleteCompany(@Param("id") long id);
}
