package com.medsync.exception;

public class AppointmentAlreadyExistsException extends RuntimeException {

	public AppointmentAlreadyExistsException(String message) {
		super(message);
	}
	
}
