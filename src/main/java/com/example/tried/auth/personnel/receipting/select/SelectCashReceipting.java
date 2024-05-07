package com.example.tried.auth.personnel.receipting.select;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectCashReceipting{

	@JsonProperty("receiverId")
	private String receiverId;

	@JsonProperty("endDate")
	private String endDate;

	@JsonProperty("function")
	private String function;

	@JsonProperty("churchCode")
	private String churchCode;

	@JsonProperty("functionType")
	private String functionType;

	@JsonProperty("typeOfPayment")
	private String typeOfPayment;

	@JsonProperty("startDate")
	private String startDate;

	public String getReceiverId(){
		return receiverId;
	}

	public String getEndDate(){
		return endDate;
	}

	public String getFunction(){
		return function;
	}

	public String getChurchCode(){
		return churchCode;
	}

	public String getFunctionType(){
		return functionType;
	}

	public String getTypeOfPayment(){
		return typeOfPayment;
	}

	public String getStartDate(){
		return startDate;
	}
}