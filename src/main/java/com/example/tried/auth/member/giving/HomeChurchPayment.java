package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HomeChurchPayment{

	@JsonProperty("payload")
	private HPayload payload;

	@JsonProperty("function")
	private String function;
}