package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class TransactionDetails{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@JsonProperty("accountDetails")
	private AccountDetails accountDetails;

	@JsonProperty("transferDuration")
	private TransferDuration transferDuration;

	@JsonProperty("specialTrustFunds")
	private HashMap<String, Integer> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Integer> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Integer> trustFunds;
}