package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginCredentials{

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("accessNumber")
	private String accessNumber;
}