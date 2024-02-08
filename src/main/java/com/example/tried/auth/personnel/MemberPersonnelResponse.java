package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberPersonnelResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("payload")
	private PersonnelPayload payload;

	@JsonProperty("function")
	private String function;

	@JsonProperty("status")
	private int status;
}