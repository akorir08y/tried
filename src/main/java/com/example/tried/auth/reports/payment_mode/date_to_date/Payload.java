package com.example.tried.auth.reports.payment_mode.date_to_date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("modeOfPayment")
	private String modeOfPayment;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("startDate")
	private String startDate;

	public Payload() {

	}

	public Payload(String conferenceName, String endDate, String modeOfPayment, String churchCode, String startDate) {
		this.conferenceName = conferenceName;
		this.endDate = endDate;
		this.modeOfPayment = modeOfPayment;
		this.churchCode = churchCode;
		this.startDate = startDate;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
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