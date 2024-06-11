package com.example.tried.dto.b2c;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ReferenceData{

	@JsonProperty("ReferenceItem")
	private ReferenceItem referenceItem;

	public ReferenceData() {

	}

	public ReferenceData(ReferenceItem referenceItem) {
		this.referenceItem = referenceItem;
	}

	public ReferenceItem getReferenceItem() {
		return referenceItem;
	}

	public void setReferenceItem(ReferenceItem referenceItem) {
		this.referenceItem = referenceItem;
	}
}