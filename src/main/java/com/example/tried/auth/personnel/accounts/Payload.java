package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Payload{

	@JsonProperty("accountList")
	private AccountList accountList;
}