package com.example.tried.auth.tracing.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("transactionId")
	private String transactionId;
}