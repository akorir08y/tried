package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("MSISDN")
	private String mSISDN;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("paymentMode")
	private String paymentMode;

	@JsonProperty("accountName")
	private String accountName;

	@JsonProperty("extraData")
	private ExtraData extraData;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("narration")
	private String narration;

	@JsonProperty("customerNames")
	private String customerNames;

	@JsonProperty("accountNumber")
	private String accountNumber;

	@JsonProperty("accessPoint")
	private String accessPoint;

	@JsonProperty("datePaymentReceived")
	private String datePaymentReceived;
}