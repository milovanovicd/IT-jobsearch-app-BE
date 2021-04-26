package com.jobsearch.services;

import com.jobsearch.data.model.Candidate;
import com.jobsearch.data.vo.CandidateVO;

public interface CandidateService {
	public Candidate findById(Long id);
	public CandidateVO update(Long id, CandidateVO candidate);
	public void delete(Long id);
}
