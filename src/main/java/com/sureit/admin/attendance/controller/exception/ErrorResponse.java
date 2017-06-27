package com.sureit.admin.attendance.controller.exception;

public class ErrorResponse {

	private int errorCode;
	private String errorMessage;

	public ErrorResponse() {

	}

	public ErrorResponse(int errorCode, String errorMessage) {
		setErrorCode(errorCode);
		setErrorMessage(errorMessage);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
