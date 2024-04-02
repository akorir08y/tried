package com.example.tried.auth.personnel.tracing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("institutionLevel")
	private String institutionLevel;

	@JsonProperty("sessionNumber")
	private String sessionNumber;

	@JsonProperty("institutionNumber")
	private String institutionNumber;

	@JsonProperty("user")
	private String user;
}