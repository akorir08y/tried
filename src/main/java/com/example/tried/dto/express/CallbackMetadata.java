package com.example.tried.dto.express;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CallbackMetadata{

	@JsonProperty("Item")
	private List<ItemItem> item;

	public CallbackMetadata() {

	}

	public CallbackMetadata(List<ItemItem> item) {
		this.item = item;
	}

	public List<ItemItem> getItem() {
		return item;
	}

	public void setItem(List<ItemItem> item) {
		this.item = item;
	}
}