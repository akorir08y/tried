package com.example.tried.auth.dashboard.deactivated;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;



public class ListDeactivatedMembersResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("members")
	private List<MembersItem> members;

	@JsonProperty("status")
	private Integer status;


	public ListDeactivatedMembersResponse() {
	}

	public ListDeactivatedMembersResponse(Integer sessionNumber, String function, List<MembersItem> members, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.members = members;
		this.status = status;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<MembersItem> getMembers() {
		return members;
	}

	public void setMembers(List<MembersItem> members) {
		this.members = members;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}