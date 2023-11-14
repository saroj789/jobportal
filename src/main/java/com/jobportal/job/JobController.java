package com.jobportal.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.job.impl.JobServiceImpl;


@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping
	public  ResponseEntity<List<Job>> allJobs() {
		List<Job> jobs= jobService.findAll();
		//return new ResponseEntity<>(jobs, HttpStatus.OK);
		return ResponseEntity.ok(jobs);
	}
	
	@PostMapping
	public ResponseEntity<String> addJobs(@RequestBody Job job) {
		Long id = jobService.createJob(job);
		return new ResponseEntity<>("Job added successfully with id "+id, HttpStatus.CREATED);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJob(@PathVariable Long id) {
		Job job = jobService.getJobById(id);
		if(job != null) {
			return new ResponseEntity<>(job,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id) {
		boolean  isDeleted = jobService.deleteJobById(id);
		if(isDeleted) {
			return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job newJob) {
		boolean  isUpdated = jobService.updateJob(id,newJob);
		if(isUpdated) {
			return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
	}

	
}
