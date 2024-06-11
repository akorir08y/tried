package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;


public class STKPushSyncResponse {

	@JsonProperty("MerchantRequestID")
	private String merchantRequestID;

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("CustomerMessage")
	private String customerMessage;

	@JsonProperty("CheckoutRequestID")
	private String checkoutRequestID;

	@JsonProperty("ResponseDescription")
	private String responseDescription;

	public STKPushSyncResponse() {

	}

	public STKPushSyncResponse(String merchantRequestID, String responseCode, String customerMessage, String checkoutRequestID, String responseDescription) {
		this.merchantRequestID = merchantRequestID;
		this.responseCode = responseCode;
		this.customerMessage = customerMessage;
		this.checkoutRequestID = checkoutRequestID;
		this.responseDescription = responseDescription;
	}

	public String getMerchantRequestID() {
		return merchantRequestID;
	}

	public void setMerchantRequestID(String merchantRequestID) {
		this.merchantRequestID = merchantRequestID;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getCheckoutRequestID() {
		return checkoutRequestID;
	}

	public void setCheckoutRequestID(String checkoutRequestID) {
		this.checkoutRequestID = checkoutRequestID;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
}