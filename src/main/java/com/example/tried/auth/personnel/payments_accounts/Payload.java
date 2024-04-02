package com.example.tried.auth.personnel.payments_accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("In-Kind")
	private String inKind;

	@JsonProperty("Mobile Money")
	private String mobileMoney;

	@JsonProperty("Bank Deposits")
	private String bankDeposits;

	@JsonProperty("typeOfAccount")
	private String typeOfAccount;

	@JsonProperty("Cash")
	private String cash;

	@JsonProperty("status")
	private Integer status;
}