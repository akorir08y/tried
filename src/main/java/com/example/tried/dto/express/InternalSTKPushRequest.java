package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternalSTKPushRequest{

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	@JsonProperty("invoice_id")
	private int invoice_id;

	@JsonProperty("institution_level")
	private String institution_level;

	@JsonProperty("institution_name")
	private String institution_name;

	@JsonProperty("institution_number")
	private String institution_number;
}