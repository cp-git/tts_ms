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

	@Column(name = "statusorder", nullable = false)
	private int statusOrder;

	@Column(name = "companyid", nullable = false)
	private int companyId;

	@Column(name = "actualstartdate", nullable = false)
	private boolean actualStartDate;

	@Column(name = "actualenddate", nullable = false)
	private boolean actualEndDate;

	@Column(name = "finalstatus", nullable = false)
	private boolean finalStatus;
	
	
	@Column(name = "statusActive")
	private boolean statusActive;

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
	 * @return the statusOrder
	 */
	public int getStatusOrder() {
		return statusOrder;
	}

	/**
	 * @param statusOrder the statusOrder to set
	 */
	public void setStatusOrder(int statusOrder) {
		this.statusOrder = statusOrder;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the actualStartDate
	 */
	public boolean isActualStartDate() {
		return actualStartDate;
	}

	/**
	 * @param actualStartDate the actualStartDate to set
	 */
	public void setActualStartDate(boolean actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	/**
	 * @return the actualEndDate
	 */
	public boolean isActualEndDate() {
		return actualEndDate;
	}

	/**
	 * @param actualEndDate the actualEndDate to set
	 */
	public void setActualEndDate(boolean actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	/**
	 * @return the finalStatus
	 */
	public boolean isFinalStatus() {
		return finalStatus;
	}

	/**
	 * @param finalStatus the finalStatus to set
	 */
	public void setFinalStatus(boolean finalStatus) {
		this.finalStatus = finalStatus;
	}
	
	

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	/**
	 * @param statusId
	 * @param statusCode
	 * @param statusDescription
	 * @param statusOrder
	 * @param companyId
	 * @param actualStartDate
	 * @param actualEndDate
	 * @param finalStatus
	 */



	public Status(int statusId, String statusCode, String statusDescription, int statusOrder, int companyId,
			boolean actualStartDate, boolean actualEndDate, boolean finalStatus, boolean statusActive) {
		super();
		this.statusId = statusId;
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
		this.statusOrder = statusOrder;
		this.companyId = companyId;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.finalStatus = finalStatus;
		this.statusActive = statusActive;
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
				+ statusDescription + ", statusOrder=" + statusOrder + ", companyId=" + companyId + ", actualStartDate="
				+ actualStartDate + ", actualEndDate=" + actualEndDate + ", finalStatus=" + finalStatus
				+ ", statusActive=" + statusActive + "]";
	}

	

}