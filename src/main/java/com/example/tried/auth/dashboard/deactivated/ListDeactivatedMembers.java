package com.example.tried.auth.dashboard.deactivated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListDeactivatedMembers{

	@JsonProperty("payload")
	private Deapayload deapayload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("authentication")
	private Authentication authentication;
}