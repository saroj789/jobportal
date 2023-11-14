package com.jobportal.company;

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


@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> companies = this.companyService.getAllCompanies();
		if(companies.size() != 0) {
			return new ResponseEntity<>(companies,HttpStatus.OK);
		}
		return new ResponseEntity<>(companies,HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/{id}")
	private ResponseEntity<Company> updateCompany(@PathVariable Long id ){
		Company company = this.companyService.getCompanyById(id);
		if(company != null) {
			return new ResponseEntity<>(company,HttpStatus.OK);	
		}
		return new ResponseEntity<>(company,HttpStatus.NOT_FOUND);	
		
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<String> updateCompany(@PathVariable Long id ,@RequestBody Company company){
		boolean isUpdated = this.companyService.updateCompany(id, company);
		if(isUpdated) {
			return new ResponseEntity<>("Company updated successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Company with ID "+id+" is not found",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	private ResponseEntity<String> craeteCompany(@RequestBody Company company){
		Company addedCompany = this.companyService.createCompany(company);
		return new ResponseEntity<>("Company with ID "+addedCompany.getId()+" is created",HttpStatus.CREATED);

	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String> deleteCompany(@PathVariable Long id){
		boolean isDeleted = this.companyService.deleteCompanyById(id);
		if(isDeleted) {
			return new ResponseEntity<>("Company deleted successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Company with ID "+id+" is not found",HttpStatus.NOT_FOUND);
		}
	}
	

}
