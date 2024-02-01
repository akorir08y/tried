package com.example.tried.dto.kra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class TaxRemittanceResponse{

	@JsonProperty("ConversationID")
	private String conversationID;

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("OriginatorConversationID")
	private String originatorConversationID;

	@JsonProperty("ResponseDescription")
	private String responseDescription;
}