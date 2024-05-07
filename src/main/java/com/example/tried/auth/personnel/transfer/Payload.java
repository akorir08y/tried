package com.example.tried.auth.personnel.transfer;

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
	private String totalAmount;

	@JsonProperty("contributorContactType")
	private String contributorContactType;

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("contributingAs")
	private String contributingAs;

	@JsonProperty("accountDetails")
	private AccountDetails accountDetails;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("contributorType")
	private String contributorType;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("transferDuration")
	private TransferDuration transferDuration;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("contributingFor")
	private String contributingFor;
}