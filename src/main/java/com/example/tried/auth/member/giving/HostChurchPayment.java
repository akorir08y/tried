package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HostChurchPayment{

	@JsonProperty("payload")
	private Gpayload gpayload;

	@JsonProperty("function")
	private String function;
}