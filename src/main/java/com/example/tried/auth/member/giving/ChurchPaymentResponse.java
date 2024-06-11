package com.example.tried.auth.member.giving;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChurchPaymentResponse{

	@JsonProperty("status")
	private Integer status;

	public ChurchPaymentResponse() {
	}

	public ChurchPaymentResponse(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}