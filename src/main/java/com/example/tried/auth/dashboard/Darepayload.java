package com.example.tried.auth.dashboard;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Darepayload{

	@JsonProperty("members")
	private List<MembersItem> members;

	public Darepayload() {
	}

	public Darepayload(List<MembersItem> members) {
		this.members = members;
	}

	public List<MembersItem> getMembers() {
		return members;
	}

	public void setMembers(List<MembersItem> members) {
		this.members = members;
	}
}