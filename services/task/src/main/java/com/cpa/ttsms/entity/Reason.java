/**
 * @author  - Code Generator
 * @createdOn -  07-08-2023
 * @Description Entity class for reason
 * 
 */

package com.cpa.ttsms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name = "reason")
public class Reason {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "taskid")
	private int taskId;

	@Column(name = "employeeid")
	private int employeeId;

	@CreationTimestamp
	@Column(name = "chgdatetime")
	private Date chgDateTime;

	@Column(name = "reason")
	private String reasonText;

	@Column(name = "statusid")
	private int statusId;

	@Column(name = "assignedto")
	private int assignedTo;

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

	/**
	 * @param id
	 * @param taskId
	 * @param employeeId
	 * @param chgDateTime
	 * @param reasonText
	 * @param statusId
	 * @param assignedTo
	 */
	public Reason(int id, int taskId, int employeeId, Date chgDateTime, String reasonText, int statusId,
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
	 * 
	 */
	public Reason() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Reason [id=" + id + ", taskId=" + taskId + ", employeeId=" + employeeId + ", chgDateTime=" + chgDateTime
				+ ", reasonText=" + reasonText + ", statusId=" + statusId + ", assignedTo=" + assignedTo + "]";
	}

}
