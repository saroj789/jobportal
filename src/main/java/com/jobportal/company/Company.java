package com.jobportal.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobportal.job.Job;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
	private List<Job> jobs;
	
	/*
	@OneToMany(cascade = CascadeType.ALL)
	private List<Review> reviews;  */
	
	

}
