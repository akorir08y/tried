package com.example.tried.auth.personnel.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MobileReceiveFundsTransfer{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;
}