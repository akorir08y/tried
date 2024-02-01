package com.example.tried.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternalTransaction{

	@JsonProperty("TransactionID")
	private String transactionID;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("Occassion")
	private String occassion;
}