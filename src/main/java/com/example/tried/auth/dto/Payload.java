package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("previousAccessNumber")
	private String previousAccessNumber;

	@JsonProperty("currentAccessNumber")
	private String currentAccessNumber;
}