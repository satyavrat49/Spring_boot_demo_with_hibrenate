package org.raunak.main.core.Exceptions;

public class InvalidRequestPayloadException extends Exception {
	private static final long serialVersionUID = -6257342189512291160L;
	public InvalidRequestPayloadException() {
	}

	public InvalidRequestPayloadException(String message) {
		super(message);
	}

	public InvalidRequestPayloadException(Throwable cause) {
		super(cause);
	}

	public InvalidRequestPayloadException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRequestPayloadException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
