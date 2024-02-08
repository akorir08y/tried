package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MembersItem{

	@JsonProperty("balance")
	private String balance;

	@JsonProperty("beneficiaryChurchNumber")
	private String beneficiaryChurchNumber;

	@JsonProperty("depositAccount")
	private String depositAccount;

	@JsonProperty("narration")
	private String narration;

	@JsonProperty("amountRefunded")
	private String amountRefunded;

	@JsonProperty("beneficiaryChurchName")
	private String beneficiaryChurchName;

	@JsonProperty("transactionDate")
	private String transactionDate;

	@JsonProperty("meansOfPayment")
	private String meansOfPayment;

	@JsonProperty("receiptNumber")
	private String receiptNumber;

	@JsonProperty("amountReceived")
	private String amountReceived;
}