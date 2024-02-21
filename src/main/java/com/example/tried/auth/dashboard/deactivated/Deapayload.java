package com.example.tried.auth.dashboard.deactivated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Deapayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("churchCode")
	private String churchCode;
}