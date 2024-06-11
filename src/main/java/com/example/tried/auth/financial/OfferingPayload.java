package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OfferingPayload{

	@JsonProperty("memberNumber")
	private String memberNumber;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("numberOfTries")
	private Integer numberOfTries;

	@JsonProperty("startDate")
	private String startDate;

	public OfferingPayload() {
	}

	public OfferingPayload(String memberNumber, String endDate, String memberName, Integer numberOfTries, String startDate) {
		this.memberNumber = memberNumber;
		this.endDate = endDate;
		this.memberName = memberName;
		this.numberOfTries = numberOfTries;
		this.startDate = startDate;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getNumberOfTries() {
		return numberOfTries;
	}

	public void setNumberOfTries(Integer numberOfTries) {
		this.numberOfTries = numberOfTries;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}