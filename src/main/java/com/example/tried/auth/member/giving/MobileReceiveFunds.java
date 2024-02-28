package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileReceiveFunds{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Rpayload rpayload;
}