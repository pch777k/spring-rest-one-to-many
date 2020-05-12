package pl.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.example.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
}
