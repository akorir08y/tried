package com.example.tried.dto.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QRCodeRequest{

	@JsonProperty("MerchantName")
	private String merchantName;

	@JsonProperty("RefNo")
	private String refNo;

	@JsonProperty("Size")
	private String size;

	@JsonProperty("Amount")
	private int amount;

	@JsonProperty("TrxCode")
	private String trxCode;

	@JsonProperty("CPI")
	private String cPI;
}