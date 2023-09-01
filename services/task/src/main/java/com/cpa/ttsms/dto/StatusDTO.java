package com.cpa.ttsms.dto;

public class StatusDTO {
	private int statusId;
	private String statusCode;
	private String statusDescription;

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public StatusDTO(int statusId, String statusCode, String statusDescription) {
		super();
		this.statusId = statusId;
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
	}

	public StatusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StatusDTO [statusId=" + statusId + ", statusCode=" + statusCode + ", statusDescription="
				+ statusDescription + "]";
	}
}
