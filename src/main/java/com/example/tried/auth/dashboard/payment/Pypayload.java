package com.example.tried.auth.dashboard.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pypayload{

	@JsonProperty("In-Kind")
	private String inKind;

	@JsonProperty("Bank Deposits")
	private String bankDeposits;

	@JsonProperty("typeOfAccount")
	private String typeOfAccount;

	@JsonProperty("Cash")
	private String cash;

	@JsonProperty("status")
	private Integer status;
}