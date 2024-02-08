package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Mempayload{

	@JsonProperty("membershipNumber")
	private String membershipNumber;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("memberDescription")
	private String memberDescription;
}