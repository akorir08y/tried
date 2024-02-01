package com.example.tried.dto.account;


import com.example.tried.dto.b2c.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckAccountBalanceAsyncResponse{

	@JsonProperty("Result")
	private Result result;
}