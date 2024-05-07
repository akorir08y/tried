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
	private HashMap<String, Object> specialTrustFunds;

	@JsonProperty("nonTrustFunds")
	private HashMap<String, Object> nonTrustFunds;

	@JsonProperty("trustFunds")
	private HashMap<String, Object> trustFunds;
}