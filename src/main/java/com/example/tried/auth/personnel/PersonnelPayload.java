package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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
}