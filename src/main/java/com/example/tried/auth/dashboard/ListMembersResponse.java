package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListMembersResponse{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("payload")
	private Darepayload darepayload;

	@JsonProperty("status")
	private Integer status;
}