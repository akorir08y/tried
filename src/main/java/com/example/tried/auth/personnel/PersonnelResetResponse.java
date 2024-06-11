package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PersonnelResetResponse{

	@JsonProperty("status")
	private int status;

	@JsonProperty("notice")
	private String notice;

	public PersonnelResetResponse() {
	}

	public PersonnelResetResponse(int status, String notice) {
		this.status = status;
		this.notice = notice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
}