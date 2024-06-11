package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestChurchDetailsWithCode{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Churchpayload churchpayload;

	public RequestChurchDetailsWithCode() {
	}

	public RequestChurchDetailsWithCode(String function, Churchpayload churchpayload) {
		this.function = function;
		this.churchpayload = churchpayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Churchpayload getChurchpayload() {
		return churchpayload;
	}

	public void setChurchpayload(Churchpayload churchpayload) {
		this.churchpayload = churchpayload;
	}
}