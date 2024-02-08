package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Profilepayload{

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("fromWithin")
	private Boolean fromWithin;
}