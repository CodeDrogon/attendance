package com.sureit.admin.attendance.controller.exception;

public class AttendanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8397379249751675567L;

	private String errorMessage;

	public AttendanceException(String errorMessage) {
		super(errorMessage);
		setErrorMessage(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
