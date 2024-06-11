package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Body{

	@JsonProperty("stkCallback")
	private StkCallback stkCallback;

	public Body() {

	}

	public Body(StkCallback stkCallback) {
		this.stkCallback = stkCallback;
	}

	public StkCallback getStkCallback() {
		return stkCallback;
	}

	public void setStkCallback(StkCallback stkCallback) {
		this.stkCallback = stkCallback;
	}
}