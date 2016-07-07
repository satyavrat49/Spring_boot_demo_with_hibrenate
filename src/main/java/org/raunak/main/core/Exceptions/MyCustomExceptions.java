package org.raunak.main.core.Exceptions;

public class MyCustomExceptions extends Exception {

private static final long serialVersionUID = -4091017533160207947L;
	
	String errorCode = null;
	String errorMessage = null;

	public MyCustomExceptions(String message) {
		super(message);
		this.errorMessage = message;
	}

	public MyCustomExceptions(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorMessage = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	
}
