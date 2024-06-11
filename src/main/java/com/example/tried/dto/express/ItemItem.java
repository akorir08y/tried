package com.example.tried.dto.express;

import com.fasterxml.jackson.annotation.JsonProperty;



public class ItemItem{

	@JsonProperty("Value")
	private Object value;

	@JsonProperty("Name")
	private String name;

	public ItemItem() {

	}

	public ItemItem(Object value, String name) {
		this.value = value;
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}