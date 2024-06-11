package com.example.tried.auth.personnel.department;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;



public class DepartmentResponse{

	@JsonProperty("sessionNumber")
	private Integer sessionNumber;

	@JsonProperty("function")
	private String function;

	@JsonProperty("departments")
	private List<String> departments;

	@JsonProperty("status")
	private Integer status;

	public DepartmentResponse() {
	}

	public DepartmentResponse(Integer sessionNumber, String function, List<String> departments, Integer status) {
		this.sessionNumber = sessionNumber;
		this.function = function;
		this.departments = departments;
		this.status = status;
	}

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}