package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthMemberRegister{

	@JsonProperty("preferredLanguage")
	private String preferredLanguage;

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("phoneNumberPrivacy")
	private String phoneNumberPrivacy;

	@JsonProperty("otherPhoneNumber")
	private String otherPhoneNumber;

	@JsonProperty("areas")
	private String areas;

	@JsonProperty("fullNames")
	private String fullNames;

	@JsonProperty("isMember")
	private String isMember;

	@JsonProperty("phoneOwner")
	private boolean phoneOwner;

	@JsonProperty("givingReceiptedTo")
	private String givingReceiptedTo;

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("residence")
	private String residence;

	@JsonProperty("email")
	private String email;
}