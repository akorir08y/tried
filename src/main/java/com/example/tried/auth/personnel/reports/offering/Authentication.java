package com.example.tried.auth.personnel.reports.offering;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("instututionName")
	private String instututionName;

	@JsonProperty("personnelName")
	private String personnelName;

	@JsonProperty("instututionLevel")
	private String instututionLevel;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("instututionNumber")
	private String instututionNumber;

	@JsonProperty("user")
	private String user;
}