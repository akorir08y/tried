package com.example.tried.auth.personnel.receipting.traced.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionDetails{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;
}