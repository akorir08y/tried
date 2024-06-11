package com.example.tried.dto.b2c;

import com.fasterxml.jackson.annotation.JsonProperty;


public class B2CTransactionAsyncResponse{

	@JsonProperty("Result")
	private Result result;

	public B2CTransactionAsyncResponse() {

	}

	public B2CTransactionAsyncResponse(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}