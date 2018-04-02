package com.levent.webservice.model.response;

public class ErrorData {
	
	private ErrorType errorType;
	private String errorMessage;
	
	public ErrorData() {}

	public ErrorData(ErrorType errorType, String errorMessage) {
		this.errorType = errorType;
		this.errorMessage = errorMessage;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public ErrorData setErrorType(ErrorType errorType) {
		this.errorType = errorType;
		
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ErrorData setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		
		return this;
	}

	@Override
	public String toString() {
		return "ErrorStatus [errorType=" + errorType + ", errorMessage=" + errorMessage + "]";
	}
	
}
