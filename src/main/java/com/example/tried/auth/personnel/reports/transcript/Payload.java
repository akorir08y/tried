package com.example.tried.auth.personnel.reports.transcript;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("churchName")
	private String churchName;


	public Payload() {
	}

	public Payload(String endDate, String churchCode, String startDate, String churchName) {
		this.endDate = endDate;
		this.churchCode = churchCode;
		this.startDate = startDate;
		this.churchName = churchName;
	}


	public Payload(String endDate, String churchCode, String meansOfPayment, String startDate, String churchName) {
		this.endDate = endDate;
		this.churchCode = churchCode;
		this.meansOfPayment = meansOfPayment;
		this.startDate = startDate;
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

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}
}