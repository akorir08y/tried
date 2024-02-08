package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Mobilepayload{

	@JsonProperty("othersDescription")
	private String othersDescription;

	@JsonProperty("othersNumber")
	private String othersNumber;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("organisationNumber")
	private String organisationNumber;
}