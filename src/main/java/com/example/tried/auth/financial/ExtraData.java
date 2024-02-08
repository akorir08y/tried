package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtraData{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;
}