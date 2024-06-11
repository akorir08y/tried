package com.example.tried.auth.reports;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Trpayload{

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("startDate")
	private String startDate;

	public Trpayload() {

	}

	public Trpayload(Integer amount, Integer sessionNumber, String endDate, FundDistribution fundDistribution, String startDate) {
		this.amount = amount;
		this.sessionNumber = sessionNumber;
		this.endDate = endDate;
		this.fundDistribution = fundDistribution;
		this.startDate = startDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public FundDistribution getFundDistribution() {
		return fundDistribution;
	}

	public void setFundDistribution(FundDistribution fundDistribution) {
		this.fundDistribution = fundDistribution;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}