package com.example.tried.dto.kra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternalTaxRequest{

	@JsonProperty("Amount")
	private String amount;
}