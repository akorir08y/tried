package com.example.tried.auth.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MobilePayForOthers{

	@JsonProperty("mobilepayload")
	private Mobilepayload mobilepayload;

	@JsonProperty("function")
	private String function;
}