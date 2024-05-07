package com.example.tried.auth.funds;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectCashReceipting{

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("typeOfPayment")
	private String typeOfPayment;

	@JsonProperty("startDate")
	private String startDate;
}