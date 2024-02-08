package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Churchpayload{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("accessNumber")
	private String accessNumber;

	@JsonProperty("mobileServiceProvider")
	private String mobileServiceProvider;

	@JsonProperty("churchCode")
	private String churchCode;

}