package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;



public class MobilePayForOthers{

	@JsonProperty("mobilepayload")
	private Mobilepayload mobilepayload;

	@JsonProperty("function")
	private String function;

	public MobilePayForOthers() {
	}

	public MobilePayForOthers(String function, Mobilepayload mobilepayload) {
		this.function = function;
		this.mobilepayload = mobilepayload;
	}

	public Mobilepayload getMobilepayload() {
		return mobilepayload;
	}

	public void setMobilepayload(Mobilepayload mobilepayload) {
		this.mobilepayload = mobilepayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}