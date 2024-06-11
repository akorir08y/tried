package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PersonnelPayload{

	@JsonProperty("organisationName")
	private String organisationName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("organisationPosition")
	private String organisationPosition;

	@JsonProperty("personnelPhone")
	private String personnelPhone;

	@JsonProperty("organisationLevel")
	private String organisationLevel;

	@JsonProperty("personnelCfmsNumber")
	private String personnelCfmsNumber;

	@JsonProperty("hasExpiredInvoices")
	private boolean hasExpiredInvoices;

	@JsonProperty("organisationNumber")
	private String organisationNumber;

	@JsonProperty("department")
	private String department;

	@JsonProperty("passwordExpiringSoon")
	private boolean passwordExpiringSoon;

	public PersonnelPayload() {
	}

	public PersonnelPayload(String organisationName, String personnelName, String organisationPosition, String personnelPhone, String organisationLevel, String personnelCfmsNumber, boolean hasExpiredInvoices, String organisationNumber, String department, boolean passwordExpiringSoon) {
		this.organisationName = organisationName;
		this.personnelName = personnelName;
		this.organisationPosition = organisationPosition;
		this.personnelPhone = personnelPhone;
		this.organisationLevel = organisationLevel;
		this.personnelCfmsNumber = personnelCfmsNumber;
		this.hasExpiredInvoices = hasExpiredInvoices;
		this.organisationNumber = organisationNumber;
		this.department = department;
		this.passwordExpiringSoon = passwordExpiringSoon;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getOrganisationPosition() {
		return organisationPosition;
	}

	public void setOrganisationPosition(String organisationPosition) {
		this.organisationPosition = organisationPosition;
	}

	public String getPersonnelPhone() {
		return personnelPhone;
	}

	public void setPersonnelPhone(String personnelPhone) {
		this.personnelPhone = personnelPhone;
	}

	public String getOrganisationLevel() {
		return organisationLevel;
	}

	public void setOrganisationLevel(String organisationLevel) {
		this.organisationLevel = organisationLevel;
	}

	public String getPersonnelCfmsNumber() {
		return personnelCfmsNumber;
	}

	public void setPersonnelCfmsNumber(String personnelCfmsNumber) {
		this.personnelCfmsNumber = personnelCfmsNumber;
	}

	public boolean isHasExpiredInvoices() {
		return hasExpiredInvoices;
	}

	public void setHasExpiredInvoices(boolean hasExpiredInvoices) {
		this.hasExpiredInvoices = hasExpiredInvoices;
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

	public boolean isPasswordExpiringSoon() {
		return passwordExpiringSoon;
	}

	public void setPasswordExpiringSoon(boolean passwordExpiringSoon) {
		this.passwordExpiringSoon = passwordExpiringSoon;
	}
}