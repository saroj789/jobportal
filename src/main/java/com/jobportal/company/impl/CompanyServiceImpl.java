package com.jobportal.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.company.Company;
import com.jobportal.company.CompanyRepository;
import com.jobportal.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public List<Company> getAllCompanies() {
		return this.companyRepository.findAll();
	}

	@Override
	public Company createCompany(Company company) {
		Company savedCompany = this.companyRepository.save(company);
		return savedCompany;
	}

	@Override
	public boolean updateCompany(Long id, Company company) {
		
		Company oldCompany = this.companyRepository.findById(id).orElse(null);
		if(oldCompany != null) {
			oldCompany.setName(company.getName());
			oldCompany.setDescription(company.getDescription());
			oldCompany.setJobs(company.getJobs());
			this.companyRepository.save(oldCompany);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Company getCompanyById(Long id) {
		Company company= this.companyRepository.findById(id).orElse(null);
		return company;
	}

	@Override
	public boolean deleteCompanyById(Long id) {

		if(this.companyRepository.existsById(id)) {
			this.companyRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
		
	}

}
