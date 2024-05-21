package com.example.tried.auth.personnel.receipting.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EditCashReceipting{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("receiptNumber")
	private String receiptNumber;
}