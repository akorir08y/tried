package com.example.tried.auth.member.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authentication{

	@JsonProperty("instututionName")
	private String instututionName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("instututionLevel")
	private String instututionLevel;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("sessionNumber")
	private Long sessionNumber;

	@JsonProperty("pin")
	private String pin;

	@JsonProperty("instututionNumber")
	private String instututionNumber;

	@JsonProperty("user")
	private String user;
}