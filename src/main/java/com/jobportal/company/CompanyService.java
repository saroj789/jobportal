package com.jobportal.company;

import java.util.List;

public interface CompanyService {
	
	Company createCompany(Company company);
	
	List<Company> getAllCompanies();
	
	boolean updateCompany(Long id, Company company);
	
	Company getCompanyById(Long id);
	
	
	boolean deleteCompanyById(Long id);
	
	

}
