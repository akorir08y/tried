package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberRegister{

	@JsonProperty("preferredLanguage")
	private String preferredLanguage;

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("phoneNumberPrivacy")
	private String phoneNumberPrivacy;

	@JsonProperty("areas")
	private String areas;

	@JsonProperty("fullNames")
	private String fullNames;

	@JsonProperty("isMember")
	private String isMember;

	@JsonProperty("phoneOwner")
	private Boolean phoneOwner;

	@JsonProperty("givingReceiptedTo")
	private String givingReceiptedTo;

	@JsonProperty("groupName")
	private String groupName;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("residence")
	private String residence;

	public MemberRegister() {
	}

	public MemberRegister(String preferredLanguage, String mobileNumber, String phoneNumberPrivacy, String areas, String fullNames, String isMember, Boolean phoneOwner, String givingReceiptedTo, String groupName, Integer sessionNumber, String pin, String churchCode, String residence) {
		this.preferredLanguage = preferredLanguage;
		this.mobileNumber = mobileNumber;
		this.phoneNumberPrivacy = phoneNumberPrivacy;
		this.areas = areas;
		this.fullNames = fullNames;
		this.isMember = isMember;
		this.phoneOwner = phoneOwner;
		this.givingReceiptedTo = givingReceiptedTo;
		this.groupName = groupName;
		this.sessionNumber = sessionNumber;
		this.pin = pin;
		this.churchCode = churchCode;
		this.residence = residence;
	}


	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumberPrivacy() {
		return phoneNumberPrivacy;
	}

	public void setPhoneNumberPrivacy(String phoneNumberPrivacy) {
		this.phoneNumberPrivacy = phoneNumberPrivacy;
	}

	public String getAreas() {
		return areas;
	}

	public void setAreas(String areas) {
		this.areas = areas;
	}

	public String getFullNames() {
		return fullNames;
	}

	public void setFullNames(String fullNames) {
		this.fullNames = fullNames;
	}

	public String getIsMember() {
		return isMember;
	}

	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}

	public Boolean getPhoneOwner() {
		return phoneOwner;
	}

	public void setPhoneOwner(Boolean phoneOwner) {
		this.phoneOwner = phoneOwner;
	}

	public String getGivingReceiptedTo() {
		return givingReceiptedTo;
	}

	public void setGivingReceiptedTo(String givingReceiptedTo) {
		this.givingReceiptedTo = givingReceiptedTo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}
}