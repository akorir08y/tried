package com.example.tried.auth.personnel.accounts.new_account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Authentication{

	@JsonProperty("password")
	private String password;

	@JsonProperty("userName")
	private String userName;
}