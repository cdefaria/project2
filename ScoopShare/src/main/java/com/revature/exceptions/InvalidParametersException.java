package com.revature.exceptions;

public class InvalidParametersException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidParametersException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParametersException(String message) {
		super(message);
	}

	public InvalidParametersException(Throwable cause) {
		super(cause);
	}
}
