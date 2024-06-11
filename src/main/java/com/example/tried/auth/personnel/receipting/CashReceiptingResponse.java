package com.example.tried.auth.personnel.receipting;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CashReceiptingResponse{

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private Integer status;

	public CashReceiptingResponse() {
	}

	public CashReceiptingResponse(String message, Integer status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}