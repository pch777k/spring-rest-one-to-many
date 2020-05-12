package pl.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.example.exception.EmployeeNotFoundException;
import pl.example.model.Company;
import pl.example.model.Employee;
import pl.example.service.CompanyService;
import pl.example.service.EmployeeService;

@RequestMapping("companies")
@RestController
public class EmployeeController {

	private EmployeeService employeeService;
	private CompanyService companyService;

	public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
		this.employeeService = employeeService;
		this.companyService = companyService;
	}
	
	@GetMapping("employees")
	public List<Employee> getEmployees() {	
		return employeeService.getEmployees();
	}
	
	@GetMapping("{companyId}/employees")
	public List<Employee> getEmployeesByCompanyId(@PathVariable (value = "companyId") Long companyId) {
		return companyService.getCompany(companyId)
							 .getEmployees();	
	}
	
	@GetMapping("{companyId}/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable (value = "companyId") Long companyId,
									@PathVariable (value = "employeeId") Long employeeId) {
		Company company =  companyService.getCompany(companyId);
		
		return company.getEmployees()
					  .stream()
					  .filter(e -> e.getId() == employeeId)
					  .findFirst()
					  .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
	}
	
	@PostMapping("{companyId}/employees")
    public void createEmployee(@PathVariable (value = "companyId") Long companyId,
                                  @RequestBody Employee employee) {
        Company company = companyService.getCompany(companyId);
        List<Employee> employees = company.getEmployees();
        employees.add(employee);
        company.setEmployees(employees);
        employeeService.addEmployee(employee);      
	}
	
	@PutMapping("{companyId}/employees/{employeeId}")
    public void updateEmployee(@PathVariable (value = "companyId") Long companyId,
    						   @PathVariable (value = "employeeId") Long employeeId,
                               @RequestBody Employee employee) {
        Company company = companyService.getCompany(companyId);
        List<Employee> employees = company.getEmployees();
        	
        Employee employeeById = employees.stream()
										 .filter(e -> e.getId() == employeeId)
										 .findFirst()
										 .orElse(null);
       	
        if(employeeById == null) {
        	employees.add(employee);
        	company.setEmployees(employees);
        	employeeService.addEmployee(employee);
        } else {
        	employeeById.setFirstName(employee.getFirstName());
        	employeeById.setLastName(employee.getLastName());
        	employeeById.setSalary(employee.getSalary());
        	employeeService.addEmployee(employeeById);
        }
	}
	
	@DeleteMapping("{companyId}/employees/{employeeId}")
	public void deleteEmployeeById(@PathVariable (value = "companyId") Long companyId,
									@PathVariable (value = "employeeId") Long employeeId) {
		 Company company =  companyService.getCompany(companyId);	
		 List<Employee> employees = company.getEmployees();
		 Employee employee = company.getEmployees()
									.stream()
									.filter(e -> e.getId() == employeeId)
									.findFirst()
									.orElseThrow(() -> new EmployeeNotFoundException(employeeId));
		 employees.remove(employee);
		 company.setEmployees(employees);
		 companyService.addCompany(company); 
	}
}
