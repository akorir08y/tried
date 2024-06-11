package com.example.tried.dto.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;


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

	public QRCodeRequest() {

	}

	public QRCodeRequest(String cPI, String trxCode, int amount, String size, String refNo, String merchantName) {
		this.cPI = cPI;
		this.trxCode = trxCode;
		this.amount = amount;
		this.size = size;
		this.refNo = refNo;
		this.merchantName = merchantName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTrxCode() {
		return trxCode;
	}

	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}

	public String getCPI() {
		return cPI;
	}

	public void setCPI(String cPI) {
		this.cPI = cPI;
	}
}