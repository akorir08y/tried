package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListMembers{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Dapayload dapayload;
}