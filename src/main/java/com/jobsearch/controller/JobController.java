package com.jobsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.vo.JobGetVO;
import com.jobsearch.data.vo.JobVO;
import com.jobsearch.services.JobService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Job Endpoint", tags = "JobEndpoint")
@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	private JobService service;

	@ApiOperation(value = "Find all jobs")
	@GetMapping
	public List<JobGetVO> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

		return DozerConverter.parseListObjects(service.findAll(pageable), JobGetVO.class);
	}

	@ApiOperation(value = "Find a specific job by ID")
	@GetMapping("/{id}")
	public JobGetVO findById(@PathVariable("id") Long id) {
		return DozerConverter.parseObject(service.findById(id), JobGetVO.class);
	}

	@ApiOperation(value = "Create a new job")
	@PostMapping()
	public JobGetVO create(@RequestBody JobVO job) {
		return service.create(job);
	}

	@ApiOperation(value = "Update a specific job")
	@PutMapping("/{id}")
	public JobGetVO update(@PathVariable Long id, @RequestBody JobVO jobVo) {
		return service.update(id, jobVo);
	}

	@ApiOperation(value = "Delete a specific job by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}