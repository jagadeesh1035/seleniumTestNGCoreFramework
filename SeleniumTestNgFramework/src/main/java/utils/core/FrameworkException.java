package utils.core;

public class FrameworkException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String errorName = "Error";

	public FrameworkException(String errorDescription) {
		super(errorDescription);
	}

	public FrameworkException(String errorName, String errorDescription) {
		super(errorDescription);
		this.errorName = errorName;
	}
}
