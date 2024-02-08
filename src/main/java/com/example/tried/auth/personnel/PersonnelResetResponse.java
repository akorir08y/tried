package com.example.tried.auth.personnel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonnelResetResponse{

	@JsonProperty("status")
	private int status;

	@JsonProperty("notice")
	private String notice;
}