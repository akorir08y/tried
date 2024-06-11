package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ListMembers{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Dapayload dapayload;

	public ListMembers() {
	}

	public ListMembers(String function, Dapayload dapayload) {
		this.function = function;
		this.dapayload = dapayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Dapayload getDapayload() {
		return dapayload;
	}

	public void setDapayload(Dapayload dapayload) {
		this.dapayload = dapayload;
	}
}