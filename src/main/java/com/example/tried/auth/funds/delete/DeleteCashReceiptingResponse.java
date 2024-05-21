package com.example.tried.auth.funds.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteCashReceiptingResponse{

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private Integer status;
}