package com.example.tried.auth.personnel.accounts.update_account;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EditValues{

	@JsonProperty("editOrganisationNumber")
	private String editOrganisationNumber;

	@JsonProperty("editAccountName")
	private String editAccountName;

	@JsonProperty("editOrganisationLevel")
	private String editOrganisationLevel;

	@JsonProperty("editOrganisationName")
	private String editOrganisationName;

	public EditValues() {
	}

	public EditValues(String editOrganisationNumber, String editAccountName, String editOrganisationLevel, String editOrganisationName) {
		this.editOrganisationNumber = editOrganisationNumber;
		this.editAccountName = editAccountName;
		this.editOrganisationLevel = editOrganisationLevel;
		this.editOrganisationName = editOrganisationName;
	}

	public String getEditOrganisationNumber() {
		return editOrganisationNumber;
	}

	public void setEditOrganisationNumber(String editOrganisationNumber) {
		this.editOrganisationNumber = editOrganisationNumber;
	}

	public String getEditAccountName() {
		return editAccountName;
	}

	public void setEditAccountName(String editAccountName) {
		this.editAccountName = editAccountName;
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