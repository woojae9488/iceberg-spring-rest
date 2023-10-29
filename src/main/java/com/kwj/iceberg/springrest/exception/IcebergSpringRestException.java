package com.kwj.iceberg.springrest.exception;

public class IcebergSpringRestException extends RuntimeException {

	public IcebergSpringRestException(String message) {
		super(message);
	}

	public IcebergSpringRestException(Throwable cause) {
		super(cause);
	}

}
