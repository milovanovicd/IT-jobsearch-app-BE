package com.jobsearch.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jobsearch.data.model.Company;
import com.jobsearch.data.vo.CompanyVO;

public interface CompanyService {
	public List<Company> findAll(Pageable pageable);
	public List<Company> searchAll(Pageable pageable, String name, List<String> industries, List<String> locations, List<String> noOfEmployees);
	public Company findById(Long id);
	public CompanyVO create(CompanyVO company);
	public CompanyVO update(Long id, CompanyVO company);
	public void delete(Long id);
}
