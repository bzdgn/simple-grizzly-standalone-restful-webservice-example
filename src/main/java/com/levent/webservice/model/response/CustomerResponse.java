package com.levent.webservice.model.response;

public class CustomerResponse {
	
	private Meta meta;
	private CustomerData data;
	
	public CustomerResponse() {}
	
	public CustomerResponse(CustomerResponse customerResponse) {
		this.meta = customerResponse.meta;
		this.data = customerResponse.data;
	}

	public Meta getMeta() {
		return meta;
	}

	public CustomerResponse setMeta(Meta meta) {
		this.meta = meta;
		
		return this;
	}

	public CustomerData getData() {
		return data;
	}

	public CustomerResponse setData(CustomerData data) {
		this.data = data;
		
		return this;
	}

	@Override
	public String toString() {
		return "CustomerResponse [meta=" + meta + ", data=" + data + "]";
	}
	
}
