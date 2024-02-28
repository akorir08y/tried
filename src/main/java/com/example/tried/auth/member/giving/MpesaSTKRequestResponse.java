package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MpesaSTKRequestResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private Integer status;
}