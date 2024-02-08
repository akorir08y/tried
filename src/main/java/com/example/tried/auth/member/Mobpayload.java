package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Mobpayload{

	@JsonProperty("othersDescription")
	private String othersDescription;

	@JsonProperty("othersNumber")
	private String othersNumber;

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("othersName")
	private String othersName;

	@JsonProperty("status")
	private Integer status;
}