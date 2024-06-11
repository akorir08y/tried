package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ExtraData{

	@JsonProperty("member")
	private Member member;

	@JsonProperty("fundDistribution")
	private FundDistribution fundDistribution;

	public ExtraData() {
	}

	public ExtraData(Member member, FundDistribution fundDistribution) {
		this.member = member;
		this.fundDistribution = fundDistribution;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public FundDistribution getFundDistribution() {
		return fundDistribution;
	}

	public void setFundDistribution(FundDistribution fundDistribution) {
		this.fundDistribution = fundDistribution;
	}

	@Override
	public String toString() {
		return "ExtraData{" +
				"member=" + member +
				", fundDistribution=" + fundDistribution +
				'}';
	}
}