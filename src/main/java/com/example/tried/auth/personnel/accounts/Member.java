package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;


public class Member {

	@JsonProperty("organisationName")
	private String organisationName;

	@JsonProperty("priority_number")
	private String priorityNumber;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("organisationLevel")
	private String organisationLevel;

	@JsonProperty("duration_of_use")
	private String durationOfUse;

	@JsonProperty("organisationNumber")
	private String organisationNumber;

	@JsonProperty("status")
	private String status;

	public Member() {
	}

	public Member(String organisationName, String priorityNumber, String accountName, String organisationLevel, String durationOfUse, String organisationNumber, String status) {
		this.organisationName = organisationName;
		this.priorityNumber = priorityNumber;
		this.accountName = accountName;
		this.organisationLevel = organisationLevel;
		this.durationOfUse = durationOfUse;
		this.organisationNumber = organisationNumber;
		this.status = status;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getPriorityNumber() {
		return priorityNumber;
	}

	public void setPriorityNumber(String priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOrganisationLevel() {
		return organisationLevel;
	}

	public void setOrganisationLevel(String organisationLevel) {
		this.organisationLevel = organisationLevel;
	}

	public String getDurationOfUse() {
		return durationOfUse;
	}

	public void setDurationOfUse(String durationOfUse) {
		this.durationOfUse = durationOfUse;
	}

	public String getOrganisationNumber() {
		return organisationNumber;
	}

	public void setOrganisationNumber(String organisationNumber) {
		this.organisationNumber = organisationNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}