package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Memberpayload{

	@JsonProperty("church_name")
	private String churchName;

	@JsonProperty("membership_number")
	private String membershipNumber;

	@JsonProperty("conference_name")
	private String conferenceName;

	@JsonProperty("church_code")
	private String churchCode;

	@JsonProperty("member_name")
	private String memberName;

	@JsonProperty("preferred_language")
	private String preferredLanguage;

	@JsonProperty("conference_number")
	private String conferenceNumber;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("departmental_accounts")
	private List<DepartmentalAccountsItem> departmentalAccounts;

	@JsonProperty("contributes_as")
	private String contributesAs;
}