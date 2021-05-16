package com.jobsearch.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Company;
import com.jobsearch.data.model.Industry;
import com.jobsearch.data.model.Job;
import com.jobsearch.data.model.JobApplication;
import com.jobsearch.data.vo.CompanyVO;
import com.jobsearch.repository.CompanyRepository;
import com.jobsearch.repository.IndustryRepository;
import com.jobsearch.repository.JobApplicationRepository;
import com.jobsearch.repository.JobRepository;
import com.jobsearch.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository repository;

	@Autowired
	IndustryRepository industryRepository;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	JobApplicationRepository jobApplicationRepository;

	public List<Company> findAll(Pageable pageable) {
		return repository.findAll(pageable).getContent();
	}

	@Override
	public List<Company> searchAll(Pageable pageable, String name, List<String> industries, List<String> locations,
			List<String> noOfEmployees) {
		List<Company> companies = this.findAll(pageable);
		List<Company> fillteredList = new ArrayList<Company>();
		int matches = 0;

		for (Company company : companies) {

			if (name != null && !company.getName().toLowerCase().contains(name.toLowerCase())) {
				continue;
			}
			
			if(name !=null ) {
				matches++;
			}
			

			if (noOfEmployees != null) {
				boolean found = false;

				for (String no : noOfEmployees) {
					int from = -1;
					int to = -1;

					if (no.endsWith("+")) {
						from = Integer.parseInt(no.substring(0, no.length() - 1));
					} else {
						String[] limits = no.split("-");
						from = Integer.parseInt(limits[0]);
						to = Integer.parseInt(limits[1]);
						;
					}

					if ((to == -1 && company.getNoOfEmployees() >= from)
							|| (company.getNoOfEmployees() >= from && company.getNoOfEmployees() <= to)) {
						found = true;
						break;
					}
				}

				if (!found) {
					continue;
				}
				
				matches++;
			}

			if (industries != null && industries.contains(company.getIndustry().getDescription())) {
				fillteredList.add(company);
				continue;
			}

			if (locations != null && locations.contains(company.getLocation())) {
				fillteredList.add(company);
				continue;
			}
			
			if( matches > 0) {
				fillteredList.add(company);
			}


		}

		return fillteredList;
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
		
		Set<Job> companyJobs = entity.getJobs();
		Set<JobApplication> jobApplications = new HashSet<JobApplication>();
		
		for (Job job : companyJobs) {
			if(!job.getJobApplications().isEmpty()) {
				jobApplications.addAll(job.getJobApplications());
				job.setJobApplications(null);
			}
			
			job.setCompany(null);
			
			job.getPosition().getJobs().remove(job);
			job.setPosition(null);
			
			job.getSeniority().getJobs().remove(job);
			job.setSeniority(null);
			
			job.setTechnologies(null);
			
			job.getStatus().getJobs().remove(job);
			job.setStatus(null);
		}
		
		entity.setJobs(null);
		entity.setIndustry(null);
		entity.setUser(null);
		
		jobApplicationRepository.deleteAll(jobApplications);
		jobRepository.deleteAll(companyJobs);
		
		
		repository.delete(entity);
	}

}
