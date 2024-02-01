package com.example.tried.dto.transactionstatus;


import com.example.tried.dto.b2c.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionStatusAsyncResponse{

	@JsonProperty("Result")
	private Result result;
}