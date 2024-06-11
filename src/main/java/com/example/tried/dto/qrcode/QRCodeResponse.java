package com.example.tried.dto.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;


public class QRCodeResponse{

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("QRCode")
	private String qRCode;

	@JsonProperty("RequestID")
	private String requestID;

	@JsonProperty("ResponseDescription")
	private String responseDescription;

	public QRCodeResponse() {

	}


	public QRCodeResponse(String responseDescription, String requestID, String qRCode, String responseCode) {
		this.responseDescription = responseDescription;
		this.requestID = requestID;
		this.qRCode = qRCode;
		this.responseCode = responseCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getqRCode() {
		return qRCode;
	}

	public void setqRCode(String qRCode) {
		this.qRCode = qRCode;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
}