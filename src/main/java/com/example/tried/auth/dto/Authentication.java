package com.example.tried.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("userName")
	private String userName;
}