package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResetPayload {

	@JsonProperty("newPassword")
	private String newPassword;

	@JsonProperty("userName")
	private String userName;
}