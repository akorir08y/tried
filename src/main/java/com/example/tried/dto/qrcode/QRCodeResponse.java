package com.example.tried.dto.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QRCodeResponse{

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("QRCode")
	private String qRCode;

	@JsonProperty("RequestID")
	private String requestID;

	@JsonProperty("ResponseDescription")
	private String responseDescription;
}