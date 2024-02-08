package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberPersonnel{

	@JsonProperty("password")
	private String password;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("connectionPurpose")
	private String connectionPurpose;

	@JsonProperty("user")
	private String user;
}