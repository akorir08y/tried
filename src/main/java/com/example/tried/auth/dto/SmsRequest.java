package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SmsRequest{

	@JsonProperty("date")
	private String date;

	@JsonProperty("clintLevel")
	private String clintLevel;

	@JsonProperty("clientAccount")
	private String clientAccount;

	@JsonProperty("phoneNumber")
	private List<String> phoneNumber;

	@JsonProperty("clientName")
	private String clientName;

	@JsonProperty("messages")
	private String messages;

	@JsonProperty("messageGroup")
	private String messageGroup;
}