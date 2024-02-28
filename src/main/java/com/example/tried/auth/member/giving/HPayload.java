package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HPayload{

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("whomToContribute")
	private String whomToContribute;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("whereContributing")
	private String whereContributing;
}