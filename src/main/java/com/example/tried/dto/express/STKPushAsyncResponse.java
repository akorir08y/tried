package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class STKPushAsyncResponse{

	@JsonProperty("Body")
	private Body body;
}