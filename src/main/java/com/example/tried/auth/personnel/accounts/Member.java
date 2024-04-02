package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;
import lombok.Data;

@Data
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
}