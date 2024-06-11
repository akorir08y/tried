package com.example.tried.dto.b2c;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ResultParameters{

	@JsonProperty("ResultParameter")
	private List<ResultParameterItem> resultParameter;

	public ResultParameters() {

	}

	public ResultParameters(List<ResultParameterItem> resultParameter) {
		this.resultParameter = resultParameter;
	}

	public List<ResultParameterItem> getResultParameter() {
		return resultParameter;
	}

	public void setResultParameter(List<ResultParameterItem> resultParameter) {
		this.resultParameter = resultParameter;
	}
}