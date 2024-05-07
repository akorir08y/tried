package com.example.tried.auth.reports.payment_mode;

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

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("instututionNumber")
	private String instututionNumber;

	@JsonProperty("user")
	private String user;
}