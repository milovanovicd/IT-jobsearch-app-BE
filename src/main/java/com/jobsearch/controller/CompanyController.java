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
import com.jobsearch.data.vo.CompanyVO;
import com.jobsearch.services.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Company Endpoint", tags = "CompanyEndpoint")
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	@Autowired
	private CompanyService service;

	@ApiOperation(value = "Find all companies")
	@GetMapping
	public List<CompanyVO> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "industries", required = false) List<String> industries,
			@RequestParam(value = "locations", required = false) List<String> locations,
			@RequestParam(value = "noOfEmployees", required = false) List<String> noOfEmployees) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		
		if(name != null || industries != null || locations != null || noOfEmployees != null) {
			return DozerConverter.parseListObjects(service.searchAll(pageable, name, industries, locations, noOfEmployees), CompanyVO.class);
		}

		return DozerConverter.parseListObjects(service.findAll(pageable), CompanyVO.class);
	}

	@ApiOperation(value = "Find a specific company by ID")
	@GetMapping("/{id}")
	public CompanyVO findById(@PathVariable("id") Long id) {
		return DozerConverter.parseObject(service.findById(id), CompanyVO.class);
	}

	@ApiOperation(value = "Create a new company")
	@PostMapping()
	public CompanyVO create(@RequestBody CompanyVO companyVo) {
		return service.create(companyVo);
	}

	@ApiOperation(value = "Update a specific company")
	@PutMapping("/{id}")
	public CompanyVO update(@PathVariable Long id, @RequestBody CompanyVO companyVo) {
		return service.update(id, companyVo);
	}

	@ApiOperation(value = "Delete a specific company by ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
