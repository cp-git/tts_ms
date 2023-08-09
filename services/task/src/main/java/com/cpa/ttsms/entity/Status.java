/**
 * @author  - Code Generator
 * @createdOn -  24-07-2023
 * @Description Entity class for Status
 * 
 */

package com.cpa.ttsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int statusId;

	@Column(name = "code", nullable = false)
	private String statusCode;

	@Column(name = "description", nullable = false)
	private String statusDescription;

	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * @param statusId
	 * @param statusCode
	 * @param statusDescription
	 */
	public Status(int statusId, String statusCode, String statusDescription) {
		super();
		this.statusId = statusId;
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
	}

	/**
	 * 
	 */
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusCode=" + statusCode + ", statusDescription="
				+ statusDescription + "]";
	}

}
