package com.jobsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.data.vo.MetadataVo;
import com.jobsearch.services.MetadataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Metadata Endpoint", tags = "MetadataEndpoint")
@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

	@Autowired
	private MetadataService service;

	@ApiOperation(value = "Get metadata")
	@GetMapping
	public MetadataVo findAll() {
		return this.service.getMetadata();
	}

}
