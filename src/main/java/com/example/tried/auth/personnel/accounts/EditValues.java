package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EditValues{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("editOrganisationNumber")
	private String editOrganisationNumber;

	@JsonProperty("editOrganisationLevel")
	private String editOrganisationLevel;

	@JsonProperty("editOrganisationName")
	private String editOrganisationName;
}