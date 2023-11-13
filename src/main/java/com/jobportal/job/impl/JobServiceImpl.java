package com.jobportal.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.job.Job;
import com.jobportal.job.JobService;


@Service
public class JobServiceImpl implements JobService {
	
	List<Job> jobs= new ArrayList<>();
	private Long nextId = 1L;
	

	@Override
	public List<Job> findAll() {
		// TODO Auto-generated method stub
		return jobs;
	}

	@Override
	public Long createJob(Job job) {
		job.setId(nextId++);
		jobs.add(job);
		return job.getId();
	}

	@Override
	public Job getJobById(Long id) {
		for (Job job : jobs) {
			if(job.getId().equals(id)) {
				return job;
			}
		}
		return null;
	}

	public boolean deleteJobById(Long id) {
		
		Iterator<Job> iterator = jobs.iterator();
		
		while (iterator.hasNext() ) {
			Job job =  iterator.next();
			if (job.getId().equals(id)) {
				iterator.remove();
				return true;
			}
			
		}
		return false;
		
		/*
		for (Job job : jobs) {
			if (job.getId().equals(id)) {
				jobs.remove(job);
				return true;
			}
		}
		return false;
		*/
	}

	public boolean updateJob(Long id, Job newJob) {
		Iterator<Job> iterator = jobs.iterator();
		
		while (iterator.hasNext() ) {
			Job job =  iterator.next();
			if (job.getId().equals(id)) {
				job.setTitle(newJob.getTitle());
				job.setDescription(newJob.getDescription());
				job.setMaxSalary(newJob.getMaxSalary());
				job.setMinSalary(newJob.getMinSalary());
				job.setLocation(newJob.getLocation());
				return true;
			}
			
		}
		return false;
	}
	
	

}
