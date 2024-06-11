package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestChurchDetailsResponse{

	@JsonProperty("phoneOneModeOfPayment")
	private String phoneOneModeOfPayment;

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

	@JsonProperty("otherPhoneNumber")
	private String otherPhoneNumber;

	@JsonProperty("memberName")
	private String memberName;

	@JsonProperty("isPessonnel")
	private Boolean isPessonnel;

	@JsonProperty("groups")
	private String groups;

	@JsonProperty("districtNumber")
	private String districtNumber;

	@JsonProperty("churchNumber")
	private String churchNumber;

	@JsonProperty("otherPhoneModeOfPayment")
	private String otherPhoneModeOfPayment;

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("trustFundAccounts")
	private String trustFundAccounts;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("specialTrustFundAccounts")
	private String specialTrustFundAccounts;

	@JsonProperty("function")
	private String function;

	@JsonProperty("availableMeansOfPayment")
	private String availableMeansOfPayment;

	@JsonProperty("status")
	private Integer status;

	public RequestChurchDetailsResponse() {
	}

	public RequestChurchDetailsResponse(String phoneOneModeOfPayment, String departmentalAccounts, String churchName, String conferenceName, String districtName, String conferenceNumber, String otherPhoneNumber, String memberName, Boolean isPessonnel, String groups, String districtNumber, String churchNumber, String otherPhoneModeOfPayment, String membershipNumber, String phoneNumber, String trustFundAccounts, Integer sessionNumber, String specialTrustFundAccounts, String function, String availableMeansOfPayment, Integer status) {
		this.phoneOneModeOfPayment = phoneOneModeOfPayment;
		this.departmentalAccounts = departmentalAccounts;
		this.churchName = churchName;
		this.conferenceName = conferenceName;
		this.districtName = districtName;
		this.conferenceNumber = conferenceNumber;
		this.otherPhoneNumber = otherPhoneNumber;
		this.memberName = memberName;
		this.isPessonnel = isPessonnel;
		this.groups = groups;
		this.districtNumber = districtNumber;
		this.churchNumber = churchNumber;
		this.otherPhoneModeOfPayment = otherPhoneModeOfPayment;
		this.membershipNumber = membershipNumber;
		this.phoneNumber = phoneNumber;
		this.trustFundAccounts = trustFundAccounts;
		this.sessionNumber = sessionNumber;
		this.specialTrustFundAccounts = specialTrustFundAccounts;
		this.function = function;
		this.availableMeansOfPayment = availableMeansOfPayment;
		this.status = status;
	}

	public String getPhoneOneModeOfPayment() {
		return phoneOneModeOfPayment;
	}

	public void setPhoneOneModeOfPayment(String phoneOneModeOfPayment) {
		this.phoneOneModeOfPayment = phoneOneModeOfPayment;
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

	public String getOtherPhoneNumber() {
		return otherPhoneNumber;
	}

	public void setOtherPhoneNumber(String otherPhoneNumber) {
		this.otherPhoneNumber = otherPhoneNumber;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Boolean getPessonnel() {
		return isPessonnel;
	}

	public void setPessonnel(Boolean pessonnel) {
		isPessonnel = pessonnel;
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

	public String getOtherPhoneModeOfPayment() {
		return otherPhoneModeOfPayment;
	}

	public void setOtherPhoneModeOfPayment(String otherPhoneModeOfPayment) {
		this.otherPhoneModeOfPayment = otherPhoneModeOfPayment;
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTrustFundAccounts() {
		return trustFundAccounts;
	}

	public void setTrustFundAccounts(String trustFundAccounts) {
		this.trustFundAccounts = trustFundAccounts;
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