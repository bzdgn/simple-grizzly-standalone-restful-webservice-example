package com.levent.webservice.model.response;

public class CustomerPayloadResponse {
	
	private Meta meta;
	private CustomerData data;
	
	public CustomerPayloadResponse() {}
	
	public CustomerPayloadResponse(CustomerPayloadResponse customerPostResponse) {
		this.meta = customerPostResponse.meta;
		this.data = customerPostResponse.data;
	}

	public Meta getMeta() {
		return meta;
	}

	public CustomerPayloadResponse setMeta(Meta meta) {
		this.meta = meta;
		
		return this;
	}

	public CustomerData getData() {
		return data;
	}

	public CustomerPayloadResponse setData(CustomerData data) {
		this.data = data;
		
		return this;
	}

	@Override
	public String toString() {
		return "CustomerPostResponse [meta=" + meta + ", data=" + data + "]";
	}

}
