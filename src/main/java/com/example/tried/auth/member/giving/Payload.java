package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("receiverName")
	private String receiverName;

	@JsonProperty("contributorName")
	private String contributorName;

	@JsonProperty("collectingParty")
	private String collectingParty;

	@JsonProperty("totalAmount")
	private Integer totalAmount;

	@JsonProperty("contributorContactType")
	private String contributorContactType;

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("contributingAs")
	private String contributingAs;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("contributorType")
	private String contributorType;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("contributingFor")
	private String contributingFor;
}