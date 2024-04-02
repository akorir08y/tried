package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CashReceiptingResponse{

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private Integer status;
}