package com.example.tried.auth.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("institutionNumber")
	private String institutionNumber;

	@JsonProperty("institutionLevel")
	private String institutionLevel;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("institutionName")
	private String institutionName;

	@JsonProperty("user")
	private String user;
}