package com.revature.exceptions;

public class FavoritesNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FavoritesNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public FavoritesNotFoundException(String message) {
		super(message);
	}

	public FavoritesNotFoundException(Throwable cause) {
		super(cause);
	}
}
