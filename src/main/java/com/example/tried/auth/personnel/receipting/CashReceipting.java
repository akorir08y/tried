package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CashReceipting{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;
}