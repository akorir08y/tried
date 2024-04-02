package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExtraData{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;
}