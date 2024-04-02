package com.example.tried.auth.personnel.accounts.update_account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}