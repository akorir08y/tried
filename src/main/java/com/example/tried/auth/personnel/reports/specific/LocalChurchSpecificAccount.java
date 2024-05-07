package com.example.tried.auth.personnel.reports.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class LocalChurchSpecificAccount{

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}