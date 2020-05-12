package pl.example.exception;

public class CompanyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(Long id) {
		super("Could not find company " + id);
	}
}
