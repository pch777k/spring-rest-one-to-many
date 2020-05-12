package pl.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.example.model.Employee;
import pl.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}
}
