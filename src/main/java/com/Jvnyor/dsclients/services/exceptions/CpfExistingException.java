package com.Jvnyor.dsclients.services.exceptions;

public class CpfExistingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CpfExistingException(String msg) {
		super(msg);
	}
}
