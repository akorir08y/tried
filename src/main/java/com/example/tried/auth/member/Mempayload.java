package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Mempayload{

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("memberDescription")
	private String memberDescription;

	public Mempayload() {
	}

	public Mempayload(String membershipNumber, String sessionNumber, String memberDescription) {
		this.membershipNumber = membershipNumber;
		this.sessionNumber = sessionNumber;
		this.memberDescription = memberDescription;
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

	public String getMemberDescription() {
		return memberDescription;
	}

	public void setMemberDescription(String memberDescription) {
		this.memberDescription = memberDescription;
	}
}