package com.github.raphaelpanta.katabankocr.exceptions;

public class EntryValidationException extends Exception {

	private static final long serialVersionUID = -4261201981816167545L;

	public EntryValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public EntryValidationException(String msg) {
		super(msg);
	}
}
