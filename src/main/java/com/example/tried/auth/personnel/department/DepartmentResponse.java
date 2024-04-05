package com.example.tried.auth.personnel.department;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class DepartmentResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("departments")
	private List<String> departments;

	@JsonProperty("status")
	private Integer status;
}