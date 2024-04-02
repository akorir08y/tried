package com.example.tried.auth.personnel.accounts.update_account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EditValues{

	@JsonProperty("editOrganisationNumber")
	private String editOrganisationNumber;

	@JsonProperty("editAccountName")
	private String editAccountName;

	@JsonProperty("editOrganisationLevel")
	private String editOrganisationLevel;

	@JsonProperty("editOrganisationName")
	private String editOrganisationName;
}