package pl.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.example.exception.CompanyNotFoundException;
import pl.example.model.Company;
import pl.example.repository.CompanyRepository;

@Service
public class CompanyService {
	
	private CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}
	
	public Company getCompany(Long id) {
		return companyRepository.findById(id)
								.orElseThrow(() -> new CompanyNotFoundException(id));
	}
	
	public void addCompany(Company company) {
		companyRepository.save(company);
	}
	
	public void updateCompany(Company company, Long id) {
		Company companyById = companyRepository.findById(id).orElse(null);
		if(companyById == null) {
			companyRepository.save(company);
		} else {
			companyById.setName(company.getName());
			companyById.setEmployees(company.getEmployees());
			companyRepository.save(companyById);
		}
	}
	
	public void deleteCompany(Long id) {
		Company companyById = companyRepository.findById(id).orElse(null);
		if(companyById == null) {
			throw new CompanyNotFoundException(id);
		}
			companyRepository.deleteById(id);	
	}
}
