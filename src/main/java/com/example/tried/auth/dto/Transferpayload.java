package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Transferpayload{

	@JsonProperty("memberNumber")
	private String memberNumber;

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("newChurchCode")
	private String newChurchCode;

	@JsonProperty("currentChurchCode")
	private String currentChurchCode;

}