package pl.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.example.model.Company;
import pl.example.service.CompanyService;

@RequestMapping("companies")
@RestController
public class CompanyController {

	private CompanyService companyService;
	
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	public List<Company> getAllCompanies(){
		return companyService.getCompanies();
	}
	
	@GetMapping("{id}")
	public Company getCompanyById(@PathVariable Long id){
		return companyService.getCompany(id);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Company created!")
	public void addCompany(@RequestBody Company company) {
		companyService.addCompany(company);
	}
	
	@PutMapping("{id}")
	public void updateCompany(@RequestBody Company company, 
							  @PathVariable Long id) {
		companyService.updateCompany(company, id);
	}
	
	@DeleteMapping("{id}")
	public void deleteCompanyById(@PathVariable Long id){
		companyService.deleteCompany(id);
	}
}
