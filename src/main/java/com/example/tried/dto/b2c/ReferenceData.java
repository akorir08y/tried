package com.example.tried.dto.b2c;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReferenceData{

	@JsonProperty("ReferenceItem")
	private ReferenceItem referenceItem;
}