package com.example.tried.dto.transactionstatus;


import com.example.tried.dto.b2c.Result;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TransactionStatusAsyncResponse{

	@JsonProperty("Result")
	private Result result;

	public TransactionStatusAsyncResponse() {

	}

	public TransactionStatusAsyncResponse(Result result) {
		this.result = result;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}