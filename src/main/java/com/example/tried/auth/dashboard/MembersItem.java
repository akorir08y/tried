package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MembersItem{

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("name")
	private String name;

	@JsonProperty("churchNumber")
	private String churchNumber;

	public MembersItem() {
	}

	public MembersItem(String phoneNumber, String name, String churchNumber) {
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.churchNumber = churchNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChurchNumber() {
		return churchNumber;
	}

	public void setChurchNumber(String churchNumber) {
		this.churchNumber = churchNumber;
	}
}