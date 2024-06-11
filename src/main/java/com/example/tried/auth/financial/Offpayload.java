package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class Offpayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("members")
	private List<MembersItem> members;

	@JsonProperty("churchNumber")
	private String churchNumber;

	public Offpayload() {
	}

	public Offpayload(String churchName, List<MembersItem> members, String churchNumber) {
		this.churchName = churchName;
		this.members = members;
		this.churchNumber = churchNumber;
	}

	public String getChurchName() {
		return churchName;
	}

	public void setChurchName(String churchName) {
		this.churchName = churchName;
	}

	public List<MembersItem> getMembers() {
		return members;
	}

	public void setMembers(List<MembersItem> members) {
		this.members = members;
	}

	public String getChurchNumber() {
		return churchNumber;
	}

	public void setChurchNumber(String churchNumber) {
		this.churchNumber = churchNumber;
	}
}