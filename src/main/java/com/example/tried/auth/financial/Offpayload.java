package com.example.tried.auth.financial;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Offpayload{

	@JsonProperty("churchName")
	private String churchName;

	@JsonProperty("members")
	private List<MembersItem> members;

	@JsonProperty("churchNumber")
	private String churchNumber;
}