package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MemberProfile{

	@JsonProperty("payload")
	private Profilepayload profilepayload;

	@JsonProperty("function")
	private String function;

	public MemberProfile() {
	}

	public MemberProfile(Profilepayload profilepayload, String function) {
		this.profilepayload = profilepayload;
		this.function = function;
	}

	public Profilepayload getProfilepayload() {
		return profilepayload;
	}

	public void setProfilepayload(Profilepayload profilepayload) {
		this.profilepayload = profilepayload;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
}