package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberProfileResponse{

	@JsonProperty("function")
	private String function;

	@JsonProperty("payload")
	private Memberpayload payload;
}