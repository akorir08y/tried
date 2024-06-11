package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EditValues{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("editOrganisationNumber")
	private String editOrganisationNumber;

	@JsonProperty("editOrganisationLevel")
	private String editOrganisationLevel;

	@JsonProperty("editOrganisationName")
	private String editOrganisationName;

	public EditValues() {
	}

	public EditValues(String sessionNumber, String editOrganisationNumber, String editOrganisationLevel, String editOrganisationName) {
		this.sessionNumber = sessionNumber;
		this.editOrganisationNumber = editOrganisationNumber;
		this.editOrganisationLevel = editOrganisationLevel;
		this.editOrganisationName = editOrganisationName;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(String sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getEditOrganisationNumber() {
		return editOrganisationNumber;
	}

	public void setEditOrganisationNumber(String editOrganisationNumber) {
		this.editOrganisationNumber = editOrganisationNumber;
	}

	public String getEditOrganisationLevel() {
		return editOrganisationLevel;
	}

	public void setEditOrganisationLevel(String editOrganisationLevel) {
		this.editOrganisationLevel = editOrganisationLevel;
	}

	public String getEditOrganisationName() {
		return editOrganisationName;
	}

	public void setEditOrganisationName(String editOrganisationName) {
		this.editOrganisationName = editOrganisationName;
	}
}