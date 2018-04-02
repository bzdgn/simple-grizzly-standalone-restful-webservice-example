package com.levent.webservice.model.response;

public class Meta {
	
	private boolean isSuccessful;
	private ErrorData errorData;
	
	public Meta() {}
	
	public Meta(Meta m) {
		this.isSuccessful = m.isSuccessful;
		this.errorData = m.errorData;
	}

	public Meta(boolean isSuccessful, ErrorData errorData) {
		this.isSuccessful = isSuccessful;
		this.errorData = errorData;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public Meta setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
		
		return this;
	}

	public ErrorData getErrorData() {
		return errorData;
	}

	public Meta setErrorData(ErrorData errorData) {
		this.errorData = errorData;
		
		return this;
	}

	@Override
	public String toString() {
		return "Meta [isSuccessful=" + isSuccessful + ", errorData=" + errorData + "]";
	}

}
