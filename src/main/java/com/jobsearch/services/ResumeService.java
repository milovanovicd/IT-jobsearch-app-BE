package com.jobsearch.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.jobsearch.data.vo.CandidateVO;
import com.jobsearch.data.vo.UploadFileResponseVO;

public interface ResumeService {
	public UploadFileResponseVO uploadResume(Long candidateId, MultipartFile file);
	public CandidateVO removeResume(Long candidateId);
	public ResponseEntity<Resource> downloadResume(String fileName, HttpServletRequest request);
}
