package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Dapayload{

	@JsonProperty("password")
	private String password;

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("user")
	private String user;
}