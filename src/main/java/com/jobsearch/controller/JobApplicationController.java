package com.jobsearch.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobsearch.converter.DozerConverter;
import com.jobsearch.data.model.JobApplication;
import com.jobsearch.data.model.JobApplicationId;
import com.jobsearch.data.vo.JobApplicationCandidateVO;
import com.jobsearch.data.vo.JobApplicationCreateVO;
import com.jobsearch.data.vo.JobApplicationJobVO;
import com.jobsearch.data.vo.JobApplicationVO;
import com.jobsearch.exception.ResourceNotFoundException;
import com.jobsearch.services.JobApplicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Job Application Endpoint", tags = "JobApplicationEndpoint")
@RestController
@RequestMapping("/api/application")
public class JobApplicationController {

	@Autowired
	private JobApplicationService service;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	@ApiOperation(value = "Find by application ID")
	@GetMapping("/{jobId}/{candidateId}")
	public JobApplicationVO findById(@PathVariable("jobId") Long jobId, @PathVariable("candidateId") Long candidateId) {
		JobApplicationId id = new JobApplicationId(jobId, candidateId);
		JobApplication jobApplication = service.findById(id);
		
		return new JobApplicationVO(
				DozerConverter.parseObject(jobApplication.getJob(), JobApplicationJobVO.class),
				DozerConverter.parseObject(jobApplication.getCandidate(), JobApplicationCandidateVO.class), 
				sdf.format(new Date(jobApplication.getCreatedOn().getTime())).toString());
	}
	
	@ApiOperation(value = "Apply for a job")
	@PostMapping()
	public JobApplicationVO apply(@RequestBody JobApplicationCreateVO jobApplication) {
		return service.create(jobApplication);
	}

	@ApiOperation(value = "Delete a specific job application by ID")
	@DeleteMapping("/{jobId}/{candidateId}")
	public ResponseEntity<?> delete(@PathVariable("jobId") Long jobId, @PathVariable("candidateId") Long candidateId) {
		try {
			JobApplicationId id = new JobApplicationId(jobId, candidateId);
            JobApplication jobApplication = service.findById(id);

    		service.delete(jobApplication);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Something went wrong...");
        }
	}
	
	@ApiOperation(value = "Delete a specific job application by jobID")
	@DeleteMapping("/{jobId}")
	public ResponseEntity<?> deleteByJobId(@PathVariable("jobId") Long jobId) {
		try {
    		service.deleteByJobId(jobId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Something went wrong...");
        }
	}

}
