package com.example.tried.dto.c2b;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Simulate Customer to Business Response
@Data
public class SimulateC2BResponse{

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("ResponseDescription")
	private String responseDescription;

	@JsonProperty("OriginatorCoversationID")
	private String originatorCoversationID;
}