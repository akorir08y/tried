package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;


public class STKPushAsyncResponse{

	@JsonProperty("Body")
	private Body body;

	public STKPushAsyncResponse() {

	}

	public STKPushAsyncResponse(Body body) {
		this.body = body;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}
}