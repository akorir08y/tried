package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Mobilepayload{

	@JsonProperty("othersDescription")
	private String othersDescription;

	@JsonProperty("othersNumber")
	private String othersNumber;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("organisationNumber")
	private String organisationNumber;

	public Mobilepayload() {
	}

	public Mobilepayload(String othersDescription, String othersNumber, Integer sessionNumber, String organisationNumber) {
		this.othersDescription = othersDescription;
		this.othersNumber = othersNumber;
		this.sessionNumber = sessionNumber;
		this.organisationNumber = organisationNumber;
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

	public String getOrganisationNumber() {
		return organisationNumber;
	}

	public void setOrganisationNumber(String organisationNumber) {
		this.organisationNumber = organisationNumber;
	}
}