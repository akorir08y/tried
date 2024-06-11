package com.example.tried.auth.personnel.receipting.local_church;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransferDuration{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("timeOfSettlement")
	private String timeOfSettlement;

	@JsonProperty("startDate")
	private String startDate;

	public TransferDuration() {
	}

	public TransferDuration(String endDate, String timeOfSettlement, String startDate) {
		this.endDate = endDate;
		this.timeOfSettlement = timeOfSettlement;
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTimeOfSettlement() {
		return timeOfSettlement;
	}

	public void setTimeOfSettlement(String timeOfSettlement) {
		this.timeOfSettlement = timeOfSettlement;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}