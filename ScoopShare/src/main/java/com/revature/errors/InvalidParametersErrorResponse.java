package com.revature.errors;

public class InvalidParametersErrorResponse {
	private int statusCode;
	private String message;
	private long timestamp;
	
	public InvalidParametersErrorResponse() { }

	public InvalidParametersErrorResponse(int statusCode, String message, long timestamp) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
