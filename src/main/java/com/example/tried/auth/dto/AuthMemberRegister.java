package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthMemberRegister{

	@JsonProperty("preferredLanguage")
	private String preferredLanguage;

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("phoneNumberPrivacy")
	private String phoneNumberPrivacy;

	@JsonProperty("otherPhoneNumber")
	private String otherPhoneNumber;

	@JsonProperty("areas")
	private String areas;

	@JsonProperty("fullNames")
	private String fullNames;

	@JsonProperty("isMember")
	private String isMember;

	@JsonProperty("phoneOwner")
	private boolean phoneOwner;

	@JsonProperty("givingReceiptedTo")
	private String givingReceiptedTo;

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("residence")
	private String residence;

	@JsonProperty("email")
	private String email;

	@JsonProperty("status")
	private int status;

	public AuthMemberRegister() {
	}

	public AuthMemberRegister(String preferredLanguage, String mobileNumber, String phoneNumberPrivacy, String otherPhoneNumber,
							  String areas, String fullNames, String isMember, boolean phoneOwner, String givingReceiptedTo,
							  String membershipNumber, int sessionNumber, String churchCode, String residence, String email,
							int status) {
		this.preferredLanguage = preferredLanguage;
		this.mobileNumber = mobileNumber;
		this.phoneNumberPrivacy = phoneNumberPrivacy;
		this.otherPhoneNumber = otherPhoneNumber;
		this.areas = areas;
		this.fullNames = fullNames;
		this.isMember = isMember;
		this.phoneOwner = phoneOwner;
		this.givingReceiptedTo = givingReceiptedTo;
		this.membershipNumber = membershipNumber;
		this.sessionNumber = sessionNumber;
		this.churchCode = churchCode;
		this.residence = residence;
		this.email = email;
		this.status = status;
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

	public String getOtherPhoneNumber() {
		return otherPhoneNumber;
	}

	public void setOtherPhoneNumber(String otherPhoneNumber) {
		this.otherPhoneNumber = otherPhoneNumber;
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

	public boolean isPhoneOwner() {
		return phoneOwner;
	}

	public void setPhoneOwner(boolean phoneOwner) {
		this.phoneOwner = phoneOwner;
	}

	public String getGivingReceiptedTo() {
		return givingReceiptedTo;
	}

	public void setGivingReceiptedTo(String givingReceiptedTo) {
		this.givingReceiptedTo = givingReceiptedTo;
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}