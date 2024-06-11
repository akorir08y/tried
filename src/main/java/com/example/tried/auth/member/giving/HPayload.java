package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HPayload{

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("whomToContribute")
	private String whomToContribute;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("whereContributing")
	private String whereContributing;

	public HPayload() {
	}

	public HPayload(String membershipNumber, String sessionNumber, String mobileNumber, String whomToContribute, String churchCode, String whereContributing) {
		this.membershipNumber = membershipNumber;
		this.sessionNumber = sessionNumber;
		this.mobileNumber = mobileNumber;
		this.whomToContribute = whomToContribute;
		this.churchCode = churchCode;
		this.whereContributing = whereContributing;
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getWhomToContribute() {
		return whomToContribute;
	}

	public void setWhomToContribute(String whomToContribute) {
		this.whomToContribute = whomToContribute;
	}

	public String getChurchCode() {
		return churchCode;
	}

	public void setChurchCode(String churchCode) {
		this.churchCode = churchCode;
	}

	public String getWhereContributing() {
		return whereContributing;
	}

	public void setWhereContributing(String whereContributing) {
		this.whereContributing = whereContributing;
	}
}