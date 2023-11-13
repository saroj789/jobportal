package com.jobportal.job.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.job.Job;
import com.jobportal.job.JobRepository;
import com.jobportal.job.JobService;


@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return this.jobRepository.findAll();
	}

	@Override
	public Long createJob(Job job) {
		Job createdJob= this.jobRepository.save(job);
		return createdJob.getId();
	}

	@Override
	public Job getJobById(Long id) {
		Job job = jobRepository.findById(id).orElse(null) ; // findById returns Optional type
		return job;
	}

	public boolean deleteJobById(Long id) {
		try {
			jobRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	public boolean updateJob(Long id, Job newJob) {
		Job job = jobRepository.findById(id).orElse(null);
				
		if (job != null )
			 {
				job.setTitle(newJob.getTitle());
				job.setDescription(newJob.getDescription());
				job.setMaxSalary(newJob.getMaxSalary());
				job.setMinSalary(newJob.getMinSalary());
				job.setLocation(newJob.getLocation());
				this.jobRepository.save(job);
				return true;
			}
		return false;
	}
	

}
