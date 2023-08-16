package com.cpa.ttsms.dto;

import java.util.Date;

public class ReasonDTO {
	private int id;

	private int taskId;

	private int employeeId;

	private Date chgDateTime;

	private String reasonText;

	private int statusId;

	private int assignedTo;

	/**
	 * 
	 */
	public ReasonDTO() {
		super();
	}

	/**
	 * @param id
	 * @param taskId
	 * @param employeeId
	 * @param chgDateTime
	 * @param reasonText
	 * @param statusId
	 * @param assignedTo
	 */
	public ReasonDTO(int id, int taskId, int employeeId, Date chgDateTime, String reasonText, int statusId,
			int assignedTo) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.employeeId = employeeId;
		this.chgDateTime = chgDateTime;
		this.reasonText = reasonText;
		this.statusId = statusId;
		this.assignedTo = assignedTo;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the chgDateTime
	 */
	public Date getChgDateTime() {
		return chgDateTime;
	}

	/**
	 * @param chgDateTime the chgDateTime to set
	 */
	public void setChgDateTime(Date chgDateTime) {
		this.chgDateTime = chgDateTime;
	}

	/**
	 * @return the reasonText
	 */
	public String getReasonText() {
		return reasonText;
	}

	/**
	 * @param reasonText the reasonText to set
	 */
	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

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
	 * @return the assignedTo
	 */
	public int getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}

}
