package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestChurchDetailsWithCodeResponse{

	@JsonProperty("departmentalAccounts")
	private String departmentalAccounts;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("conferenceName")
	private String conferenceName;

	@JsonProperty("districtName")
	private String districtName;

	@JsonProperty("conferenceNumber")
	private String conferenceNumber;

	@JsonProperty("groups")
	private String groups;

	@JsonProperty("districtNumber")
	private String districtNumber;

	@JsonProperty("churchNumber")
	private String churchNumber;

	@JsonProperty("settlementAccountNumber")
	private String settlementAccountNumber;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("specialTrustFundAccounts")
	private String specialTrustFundAccounts;

	@JsonProperty("function")
	private String function;

	@JsonProperty("redactedSettlementAccountNumber")
	private String redactedSettlementAccountNumber;

	@JsonProperty("availableMeansOfPayment")
	private String availableMeansOfPayment;

	@JsonProperty("status")
	private Integer status;

	public RequestChurchDetailsWithCodeResponse() {
	}

	public RequestChurchDetailsWithCodeResponse(String departmentalAccounts, String churchName, String conferenceName, String districtName, String conferenceNumber, String groups, String districtNumber, String churchNumber, String settlementAccountNumber, Integer sessionNumber, String specialTrustFundAccounts, String function, String redactedSettlementAccountNumber, String availableMeansOfPayment, Integer status) {
		this.departmentalAccounts = departmentalAccounts;
		this.churchName = churchName;
		this.conferenceName = conferenceName;
		this.districtName = districtName;
		this.conferenceNumber = conferenceNumber;
		this.groups = groups;
		this.districtNumber = districtNumber;
		this.churchNumber = churchNumber;
		this.settlementAccountNumber = settlementAccountNumber;
		this.sessionNumber = sessionNumber;
		this.specialTrustFundAccounts = specialTrustFundAccounts;
		this.function = function;
		this.redactedSettlementAccountNumber = redactedSettlementAccountNumber;
		this.availableMeansOfPayment = availableMeansOfPayment;
		this.status = status;
	}

	public String getDepartmentalAccounts() {
		return departmentalAccounts;
	}

	public void setDepartmentalAccounts(String departmentalAccounts) {
		this.departmentalAccounts = departmentalAccounts;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getConferenceNumber() {
		return conferenceNumber;
	}

	public void setConferenceNumber(String conferenceNumber) {
		this.conferenceNumber = conferenceNumber;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getDistrictNumber() {
		return districtNumber;
	}

	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}

	public String getChurchNumber() {
		return churchNumber;
	}

	public void setChurchNumber(String churchNumber) {
		this.churchNumber = churchNumber;
	}

	public String getSettlementAccountNumber() {
		return settlementAccountNumber;
	}

	public void setSettlementAccountNumber(String settlementAccountNumber) {
		this.settlementAccountNumber = settlementAccountNumber;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getSpecialTrustFundAccounts() {
		return specialTrustFundAccounts;
	}

	public void setSpecialTrustFundAccounts(String specialTrustFundAccounts) {
		this.specialTrustFundAccounts = specialTrustFundAccounts;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getRedactedSettlementAccountNumber() {
		return redactedSettlementAccountNumber;
	}

	public void setRedactedSettlementAccountNumber(String redactedSettlementAccountNumber) {
		this.redactedSettlementAccountNumber = redactedSettlementAccountNumber;
	}

	public String getAvailableMeansOfPayment() {
		return availableMeansOfPayment;
	}

	public void setAvailableMeansOfPayment(String availableMeansOfPayment) {
		this.availableMeansOfPayment = availableMeansOfPayment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}