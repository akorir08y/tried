package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Mobpayload{

	@JsonProperty("othersDescription")
	private String othersDescription;

	@JsonProperty("othersNumber")
	private String othersNumber;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("othersName")
	private String othersName;

	@JsonProperty("status")
	private Integer status;

	public Mobpayload() {
	}

	public Mobpayload(String othersDescription, String othersNumber, Integer sessionNumber, String othersName, Integer status) {
		this.othersDescription = othersDescription;
		this.othersNumber = othersNumber;
		this.sessionNumber = sessionNumber;
		this.othersName = othersName;
		this.status = status;
	}

	public String getOthersDescription() {
		return othersDescription;
	}

	public void setOthersDescription(String othersDescription) {
		this.othersDescription = othersDescription;
	}

	public String getOthersNumber() {
		return othersNumber;
	}

	public void setOthersNumber(String othersNumber) {
		this.othersNumber = othersNumber;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getOthersName() {
		return othersName;
	}

	public void setOthersName(String othersName) {
		this.othersName = othersName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}