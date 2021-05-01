package com.jobsearch.services.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jobsearch.controller.ResumeController;
import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.Candidate;
import com.jobsearch.data.vo.CandidateVO;
import com.jobsearch.data.vo.UploadFileResponseVO;
import com.jobsearch.services.CandidateService;
import com.jobsearch.services.FileStorageService;
import com.jobsearch.services.ResumeService;

@Service
public class ResumerServiceImpl implements ResumeService{
	
	private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private CandidateService candidateService;
	
	
	public UploadFileResponseVO uploadResume(Long candidateId, MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/resume/download/")
				.path(fileName)
				.toUriString();
		
		Candidate candidate = this.candidateService.findById(candidateId);
		candidate.setResume(fileDownloadUri);
		this.candidateService.update(candidateId, DozerConverter.parseObject(candidate, CandidateVO.class));
		
		return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	public CandidateVO removeResume(Long candidateId) {
		Candidate candidate = this.candidateService.findById(candidateId);
		candidate.setResume(null);
		return this.candidateService.update(candidateId, DozerConverter.parseObject(candidate, CandidateVO.class));
	}
	
	public ResponseEntity<Resource> downloadResume(String fileName, HttpServletRequest request) {
		
		Resource resource  = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("Could not determine file type!");
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
				.body(resource);
	}
}
