package com.example.tried.auth.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MembersItem{

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("name")
	private String name;

	@JsonProperty("churchNumber")
	private String churchNumber;
}