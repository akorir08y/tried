package com.example.tried.auth.personnel.accounts.new_account;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Payload{

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("organisationLevel")
	private String organisationLevel;

	@JsonProperty("priorityNumber")
	private String priorityNumber;

	@JsonProperty("duration")
	private String duration;

	@JsonProperty("expiryDate")
	private String expiryDate;

	@JsonProperty("organisationName")
	private String organisationName;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("selectMode")
	private Boolean selectMode;

	@JsonProperty("editMode")
	private Boolean editMode;

	@JsonProperty("newEntryMode")
	private Boolean newEntryMode;

	@JsonProperty("organisationNumber")
	private String organisationNumber;

	@JsonProperty("department")
	private String department;

	@JsonProperty("status")
	private String status;

	public Payload() {
	}

	public Payload(String accountName, String organisationLevel, String priorityNumber, String duration, String expiryDate, String organisationName, Integer sessionNumber, Boolean selectMode, Boolean editMode, Boolean newEntryMode, String organisationNumber, String department, String status) {
		this.accountName = accountName;
		this.organisationLevel = organisationLevel;
		this.priorityNumber = priorityNumber;
		this.duration = duration;
		this.expiryDate = expiryDate;
		this.organisationName = organisationName;
		this.sessionNumber = sessionNumber;
		this.selectMode = selectMode;
		this.editMode = editMode;
		this.newEntryMode = newEntryMode;
		this.organisationNumber = organisationNumber;
		this.department = department;
		this.status = status;
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

	public String getPriorityNumber() {
		return priorityNumber;
	}

	public void setPriorityNumber(String priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public Boolean getSelectMode() {
		return selectMode;
	}

	public void setSelectMode(Boolean selectMode) {
		this.selectMode = selectMode;
	}

	public Boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
	}

	public Boolean getNewEntryMode() {
		return newEntryMode;
	}

	public void setNewEntryMode(Boolean newEntryMode) {
		this.newEntryMode = newEntryMode;
	}

	public String getOrganisationNumber() {
		return organisationNumber;
	}

	public void setOrganisationNumber(String organisationNumber) {
		this.organisationNumber = organisationNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}