package com.jobsearch.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Company;
import com.jobsearch.data.model.Job;
import com.jobsearch.data.model.Position;
import com.jobsearch.data.model.Seniority;
import com.jobsearch.data.model.Technology;
import com.jobsearch.data.vo.JobGetVO;
import com.jobsearch.data.vo.JobVO;
import com.jobsearch.repository.CompanyRepository;
import com.jobsearch.repository.JobRepository;
import com.jobsearch.repository.PositionRepository;
import com.jobsearch.repository.SeniorityRepository;
import com.jobsearch.repository.TechnologyRepository;
import com.jobsearch.services.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobRepository repository;

	@Autowired
	PositionRepository positionRepository;

	@Autowired
	SeniorityRepository seniorityRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	TechnologyRepository technologyRepository;

	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	public List<Job> findAll(Pageable pageable) {
		return repository.findAll(pageable).getContent();

	}

	public Job findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		
	}

	public JobGetVO create(JobVO job) {
		Position optionalPosition = positionRepository.findByDescription(job.getPosition());

		Optional<Seniority> optionalSeniority = seniorityRepository.findById(job.getSeniority());

		// Optional sluzi kao wrapper, gde ako ne nadje iz baze entitet, ne puca program
		// ako uzmes obican Entity.class, onda ce puci program ako ne nadje u bazi
		Optional<Company> optionalCompany = Optional.ofNullable(companyRepository.findById(job.getCompanyId())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this company ID")));

		Job entity = new Job();

		if (optionalPosition == null) {
			Position newPosition = positionRepository.save(new Position(job.getPosition()));
			entity.setPosition(newPosition);
		} else {
			entity.setPosition(optionalPosition);
		}

		if (optionalSeniority.isPresent()) {
			entity.setSeniority(optionalSeniority.get());
		}

		if (optionalCompany.isPresent()) {
			entity.setCompany(optionalCompany.get());
		}

		List<Technology> newTechList = new ArrayList<Technology>(); // lista nepostojecih tehnologija (u bazi)
		List<Technology> repoTechList = new ArrayList<Technology>(); // lista postojecih tehnologija

		for (String techName : job.getTechnologies()) {
			Technology optionalTech = technologyRepository.findByDescription(techName);

			if (optionalTech == null) {
				newTechList.add(new Technology(techName));
			} else {
				repoTechList.add(optionalTech);
			}
		}

		if (!newTechList.isEmpty()) {
			repoTechList.addAll(technologyRepository.saveAll(newTechList));
		}

		try {
			entity.setName(job.getName());
			entity.setDescription(job.getDescription());
			entity.setPublishedDate(sdf.parse(job.getPublishedDate()));
			entity.setDeadlineDate(sdf.parse(job.getDeadlineDate()));
			entity.setTechnologies(repoTechList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DozerConverter.parseObject(repository.save(entity), JobGetVO.class);
	}

	public JobGetVO update(Long id, JobVO job) {
		Position optionalPosition = positionRepository.findByDescription(job.getPosition());

		Optional<Seniority> optionalSeniority = seniorityRepository.findById(job.getSeniority());

		// Optional sluzi kao wrapper, gde ako ne nadje iz baze entitet, ne puca program
		// ako uzmes obican Entity.class, onda ce puci program ako ne nadje u bazi
		Optional<Company> optionalCompany = Optional.ofNullable(companyRepository.findById(job.getCompanyId())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this company ID")));

		Job entity = this.findById(id);

		if (optionalPosition == null) {
			Position newPosition = positionRepository.save(new Position(job.getPosition()));
			entity.setPosition(newPosition);
		} else {
			entity.setPosition(optionalPosition);
		}

		if (optionalSeniority.isPresent()) {
			entity.setSeniority(optionalSeniority.get());
		}
	

		if (optionalCompany.isPresent()) {
			entity.setCompany(optionalCompany.get());
		}

		List<Technology> newTechList = new ArrayList<Technology>(); // lista nepostojecih tehnologija (u bazi)
		List<Technology> repoTechList = new ArrayList<Technology>(); // lista postojecih tehnologija

		for (String techName : job.getTechnologies()) {
			Technology optionalTech = technologyRepository.findByDescription(techName);

			if (optionalTech == null) {
				newTechList.add(new Technology(techName));
			} else {
				repoTechList.add(optionalTech);
			}
		}

		if (!newTechList.isEmpty()) {
			repoTechList.addAll(technologyRepository.saveAll(newTechList));
		}

		try {
			entity.setName(job.getName());
			entity.setDescription(job.getDescription());
			entity.setPublishedDate(sdf.parse(job.getPublishedDate()));
			entity.setDeadlineDate(sdf.parse(job.getDeadlineDate()));
			entity.setTechnologies(repoTechList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DozerConverter.parseObject(repository.save(entity), JobGetVO.class);
	}

	public void delete(Job job) {
		
		job.setCompany(null);
		job.setPosition(null);
		job.setSeniority(null);
		job.setTechnologies(null);
		
		repository.deleteJob(job.getId());
//		repository.deleteById(job.getId());
	}

}
