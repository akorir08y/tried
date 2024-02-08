package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberTransferResponse{

	@JsonProperty("sessionNumber")
	private int sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("state")
	private String state;

	@JsonProperty("status")
	private int status;

	public int getSessionNumber(){
		return sessionNumber;
	}

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