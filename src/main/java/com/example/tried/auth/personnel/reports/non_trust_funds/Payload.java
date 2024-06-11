package com.example.tried.auth.personnel.reports.non_trust_funds;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("group")
	private String group;

	public Payload() {
	}

	public Payload(String churchName, String endDate, String churchCode, String meansOfPayment, String startDate, String group) {
		this.churchName = churchName;
		this.endDate = endDate;
		this.churchCode = churchCode;
		this.meansOfPayment = meansOfPayment;
		this.startDate = startDate;
		this.group = group;
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

	public String getMeansOfPayment() {
		return meansOfPayment;
	}

	public void setMeansOfPayment(String meansOfPayment) {
		this.meansOfPayment = meansOfPayment;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}