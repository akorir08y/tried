package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExtraData{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	@Override
	public String toString() {
		return "ExtraData{" +
				"member=" + member +
				", fundDistribution=" + fundDistribution +
				'}';
	}
}