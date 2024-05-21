package com.example.tried.auth.funds.non;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Funds{

	@JsonProperty("Combined Offerings")
	private Object combinedOfferings;

	@JsonProperty("openingCombinedOfferings")
	private Object openingCombinedOfferings;
}