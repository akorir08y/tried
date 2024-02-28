package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class MpesaSTKRequest{

	@JsonProperty("amount")
	private Integer amount;

	@JsonProperty("cfmsTransactionId")
	private String cfmsTransactionId;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("channel")
	private String channel;

	@JsonProperty("accountNumber")
	private String accountNumber;
}