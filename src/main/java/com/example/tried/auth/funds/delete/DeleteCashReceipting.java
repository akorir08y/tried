package com.example.tried.auth.funds.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteCashReceipting{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("function")
	private String function;

	@JsonProperty("contributorContact")
	private String contributorContact;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("receiptNumber")
	private String receiptNumber;
}