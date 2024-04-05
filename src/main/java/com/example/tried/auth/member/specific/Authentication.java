package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authentication{

	@JsonProperty("instututionName")
	private String institutionName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("instututionLevel")
	private String institutionLevel;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("sessionNumber")
	private Long sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("instututionNumber")
	private String institutionNumber;

	@JsonProperty("user")
	private String user;
}