package com.jobsearch.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Company;
import com.jobsearch.data.model.Industry;
import com.jobsearch.data.vo.CompanyVO;
import com.jobsearch.repository.CompanyRepository;
import com.jobsearch.repository.IndustryRepository;
import com.jobsearch.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyRepository repository;

	@Autowired
	IndustryRepository industryRepository;

	public List<Company> findAll(Pageable pageable) {
		return repository.findAll(pageable).getContent();

	}

	public Company findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

	}

	public CompanyVO create(CompanyVO company) {
		Industry optionalIndustry = industryRepository.findByDescription(company.getIndustry());

		Company entity = new Company();

		if (optionalIndustry == null) {
			Industry newIndustry = industryRepository.save(new Industry(company.getIndustry()));
			entity.setIndustry(newIndustry);
		} else {
			entity.setIndustry(optionalIndustry);
		}

		entity.setName(company.getName());
		entity.setDescription(company.getDescription());
		entity.setLocation(company.getLocation());
		entity.setNoOfEmployees(company.getNoOfEmployees());

		return DozerConverter.parseObject(repository.save(entity), CompanyVO.class);
	}

	public CompanyVO update(Long id, CompanyVO company) {
		Industry optionalIndustry = industryRepository.findByDescription(company.getIndustry());

		Company entity = this.findById(id);

		if (optionalIndustry == null) {
			Industry newIndustry = industryRepository.save(new Industry(company.getIndustry()));
			entity.setIndustry(newIndustry);
		} else {
			entity.setIndustry(optionalIndustry);
		}

		entity.setName(company.getName());
		entity.setDescription(company.getDescription());
		entity.setLocation(company.getLocation());
		entity.setNoOfEmployees(company.getNoOfEmployees());

		return DozerConverter.parseObject(repository.save(entity), CompanyVO.class);
	}

	public void delete(Long id) {
		Company entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

		repository.delete(entity);
	}

}
