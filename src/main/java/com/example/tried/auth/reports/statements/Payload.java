package com.example.tried.auth.reports.statements;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("startDate")
	private String startDate;

	public Payload() {

	}

	public Payload(String churchName, String endDate, String churchCode, String startDate) {
		this.churchName = churchName;
		this.endDate = endDate;
		this.churchCode = churchCode;
		this.startDate = startDate;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}