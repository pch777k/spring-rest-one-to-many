package pl.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CompanyNotFoundAdvice {
	
	@ExceptionHandler(CompanyNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String companyNotFoundHandler(CompanyNotFoundException ex) {
	        return ex.getMessage();
	}
}
