package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ListMembersResponse{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("payload")
	private Darepayload darepayload;

	@JsonProperty("status")
	private Integer status;

	public ListMembersResponse() {
	}

	public ListMembersResponse(Integer status, Darepayload darepayload, String churchCode, String function, String churchName) {
		this.status = status;
		this.darepayload = darepayload;
		this.churchCode = churchCode;
		this.function = function;
		this.churchName = churchName;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public Darepayload getDarepayload() {
		return darepayload;
	}

	public void setDarepayload(Darepayload darepayload) {
		this.darepayload = darepayload;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}