package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalChurchAccounts{

	@JsonProperty("editValues")
	private EditValues editValues;

	@JsonProperty("payload")
	private SelectLocalChurchPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;

	public LocalChurchAccounts() {
	}

	public LocalChurchAccounts(EditValues editValues, SelectLocalChurchPayload payload, String function, Authentication authentication) {
		this.editValues = editValues;
		this.payload = payload;
		this.function = function;
		this.authentication = authentication;
	}

	public EditValues getEditValues() {
		return editValues;
	}

	public void setEditValues(EditValues editValues) {
		this.editValues = editValues;
	}

	public SelectLocalChurchPayload getPayload() {
		return payload;
	}

	public void setPayload(SelectLocalChurchPayload payload) {
		this.payload = payload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
}