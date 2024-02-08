package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OfferingAuthentication {

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("institutionNumber")
	private String institutionNumber;

	@JsonProperty("institutionLevel")
	private String institutionLevel;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("user")
	private String user;
}