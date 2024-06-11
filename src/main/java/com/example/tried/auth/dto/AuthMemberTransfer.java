package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AuthMemberTransfer{

	@JsonProperty("memberNumber")
	private String memberNumber;

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("newChurchCode")
	private String newChurchCode;

	@JsonProperty("currentChurchCode")
	private String currentChurchCode;

	public AuthMemberTransfer() {
	}

	public AuthMemberTransfer(String memberNumber, int sessionNumber, String newChurchCode, String currentChurchCode) {
		this.memberNumber = memberNumber;
		this.sessionNumber = sessionNumber;
		this.newChurchCode = newChurchCode;
		this.currentChurchCode = currentChurchCode;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getNewChurchCode() {
		return newChurchCode;
	}

	public void setNewChurchCode(String newChurchCode) {
		this.newChurchCode = newChurchCode;
	}

	public String getCurrentChurchCode() {
		return currentChurchCode;
	}

	public void setCurrentChurchCode(String currentChurchCode) {
		this.currentChurchCode = currentChurchCode;
	}
}