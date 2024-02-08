package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberPersonnelReset{

	@JsonProperty("payload")
	private ResetPayload payload;

	@JsonProperty("function")
	private String function;
}