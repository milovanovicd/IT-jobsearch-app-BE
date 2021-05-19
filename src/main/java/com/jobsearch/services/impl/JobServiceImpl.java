package com.jobsearch.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Company;
import com.jobsearch.data.model.Job;
import com.jobsearch.data.model.Position;
import com.jobsearch.data.model.Seniority;
import com.jobsearch.data.model.Status;
import com.jobsearch.data.model.Technology;
import com.jobsearch.data.vo.JobGetVO;
import com.jobsearch.data.vo.JobVO;
import com.jobsearch.exception.ResourceNotFoundException;
import com.jobsearch.repository.CompanyRepository;
import com.jobsearch.repository.JobRepository;
import com.jobsearch.repository.PositionRepository;
import com.jobsearch.repository.SeniorityRepository;
import com.jobsearch.repository.StatusRepository;
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

	@Autowired
	StatusRepository statusRepository;

	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	public List<Job> findAll(Pageable pageable) {
		List<Job> allJobs = repository.findAll(pageable).getContent();
		List<Job> filteredJobs = new ArrayList<Job>();

		if (!allJobs.isEmpty() && allJobs != null) {
			for (Job job : allJobs) {
				if (job.getStatus() != null && job.getStatus().getId() == 1) {
					filteredJobs.add(job);
				}
			}
		}

		return filteredJobs;
	}

	public Job findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

	}

	public JobGetVO create(JobVO job) {
		Position optionalPosition = positionRepository.findByDescription(job.getPosition());

		Optional<Seniority> optionalSeniority = seniorityRepository.findById(job.getSeniority());

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
			entity.setStatus(statusRepository.findByDescription("ACTIVE"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DozerConverter.parseObject(repository.save(entity), JobGetVO.class);
	}

	public JobGetVO update(Long id, JobVO job) {
		Position optionalPosition = positionRepository.findByDescription(job.getPosition());

		Optional<Seniority> optionalSeniority = seniorityRepository.findById(job.getSeniority());
		Optional<Status> optionalStatus = statusRepository.findById(job.getStatus());

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

		if (optionalStatus.isPresent()) {
			entity.setStatus(optionalStatus.get());
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
		job.setStatus(null);

		repository.deleteJob(job.getId());
	}

	@Override
	public List<Job> searchAll(Pageable pageable, String name, List<String> technologies, List<String> positions,
			List<String> seniorities) {
		List<Job> jobs = this.findAll(pageable);
		List<Job> fillteredList = new ArrayList<Job>();
		int matches = 0;

		if (!jobs.isEmpty() && jobs != null) {
			for (Job job : jobs) {

				if (name != null && !job.getName().toLowerCase().contains(name.toLowerCase())) {
					continue;
				}

				if (name != null) {
					matches++;
				}

				if (technologies != null) {
					boolean techFound = false;

					for (Technology tech : job.getTechnologies()) {
						if (technologies.contains(tech.getDescription())) {
							techFound = true;
							break;
						}
					}

					if (!techFound) {
						continue;
					}

					matches++;
				}

				if (positions != null && positions.contains(job.getPosition().getDescription())) {
					fillteredList.add(job);
					continue;
				}

				if (seniorities != null && seniorities.contains(job.getSeniority().getDescription())) {
					fillteredList.add(job);
					continue;
				}

				if (matches > 0) {
					fillteredList.add(job);
				}

			}
		}

		return fillteredList;
	}

	@Override
	public void checkExpireDates() {
		List<Job> allJobs = repository.findAll();
		List<Job> expiredJobs = new ArrayList<Job>();

		Status statusExpired = this.statusRepository.findByDescription("Expired");

		if (!allJobs.isEmpty() && allJobs != null) {
			for (Job job : allJobs) {
				boolean expired = job.getDeadlineDate().before(new Date());

				if (expired == true) {
					job.setStatus(statusExpired);
					expiredJobs.add(job);
				}
			}

			if (!expiredJobs.isEmpty()) {
				this.repository.saveAll(expiredJobs);
			}
		}

	}

}
