package com.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.vo.CandidateVO;
import com.jobsearch.services.CandidateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Candidate Endpoint", tags = "CandidateEndpoint")
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

	@Autowired
	private CandidateService service;

	@ApiOperation(value = "Find a specific company by ID")
	@GetMapping("/{id}")
	public CandidateVO findById(@PathVariable("id") Long id) {
		return DozerConverter.parseObject(service.findById(id), CandidateVO.class);
	}

	@ApiOperation(value = "Update a specific candidate")
	@PutMapping("/{id}")
	public CandidateVO update(@PathVariable Long id, @RequestBody CandidateVO candidateVo) {
		return service.update(id, candidateVo);
	}

	@ApiOperation(value = "Delete a specific candidate by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
