package com.example.tried.auth.personnel.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SelectLocalChurchPayload{

	@JsonProperty("selectMode")
	private Boolean selectMode;

	@JsonProperty("editMode")
	private Boolean editMode;

	@JsonProperty("newEntryMode")
	private Boolean newEntryMode;

	public SelectLocalChurchPayload() {
	}

	public SelectLocalChurchPayload(Boolean selectMode, Boolean editMode, Boolean newEntryMode) {
		this.selectMode = selectMode;
		this.editMode = editMode;
		this.newEntryMode = newEntryMode;
	}

	public Boolean getSelectMode() {
		return selectMode;
	}

	public void setSelectMode(Boolean selectMode) {
		this.selectMode = selectMode;
	}

	public Boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
	}

	public Boolean getNewEntryMode() {
		return newEntryMode;
	}

	public void setNewEntryMode(Boolean newEntryMode) {
		this.newEntryMode = newEntryMode;
	}
}