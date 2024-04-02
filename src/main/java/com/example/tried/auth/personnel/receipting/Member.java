package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Member{

	@JsonProperty("receiverContactType")
	private String receiverContactType;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("receiverContact")
	private String receiverContact;

	@JsonProperty("receiverName")
	private String receiverName;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("collectingParty")
	private String collectingParty;

	@JsonProperty("contributorContactType")
	private String contributorContactType;

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("contributingAs")
	private String contributingAs;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("contributorType")
	private String contributorType;

	@JsonProperty("contributingFor")
	private String contributingFor;
}