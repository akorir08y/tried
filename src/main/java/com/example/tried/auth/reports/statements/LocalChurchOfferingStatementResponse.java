package com.example.tried.auth.reports.statements;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LocalChurchOfferingStatementResponse{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("payload")
	private Lspayload lspayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private Integer status;

	public LocalChurchOfferingStatementResponse() {

	}

	public LocalChurchOfferingStatementResponse(String sessionNumber, Lspayload lspayload, String function, Integer status) {
		this.sessionNumber = sessionNumber;
		this.lspayload = lspayload;
		this.function = function;
		this.status = status;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public Lspayload getLspayload() {
		return lspayload;
	}

	public void setLspayload(Lspayload lspayload) {
		this.lspayload = lspayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}