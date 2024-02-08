package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SMSResponse{

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private int status;

	public String getFunction(){
		return function;
	}

	public String getState(){
		return state;
	}

	public int getStatus(){
		return status;
	}
}