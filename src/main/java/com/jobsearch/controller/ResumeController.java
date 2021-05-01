package com.jobsearch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobsearch.data.vo.CandidateVO;
import com.jobsearch.data.vo.UploadFileResponseVO;
import com.jobsearch.services.ResumeService;

import io.swagger.annotations.Api;

@Api(value = "Resume Endpoint", tags = "ResumeEndpoint")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {
	
	@Autowired
	ResumeService service;

	@PostMapping("/upload")
	public UploadFileResponseVO uploadResume(@RequestParam("id") Long candidateId,
			@RequestParam("file") MultipartFile file) {
		return service.uploadResume(candidateId, file);
	}

	@PutMapping("/remove/{id}")
	public CandidateVO removeResume(@PathVariable("id") Long candidateId) {
		return this.service.removeResume(candidateId);
	}

	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadResume(@PathVariable String fileName, HttpServletRequest request) {
		return this.service.downloadResume(fileName, request);
	}
}
