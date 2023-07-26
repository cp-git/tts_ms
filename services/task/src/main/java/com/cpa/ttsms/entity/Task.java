/**
 * @author  - Code Generator
 * @createdOn -  25-07-2023
 * @Description Entity class for task
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

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int taskId;

	@Column(name = "name", nullable = false)
	private String taskName;

	@Column(name = "description", nullable = false)
	private String taskDescription;

	@Column(name = "createdby", nullable = false)
	private int taskCreatedBy;

	@Column(name = "assignedto", nullable = false)
	private int taskAssignedTo;

	@Column(name = "status", nullable = false)
	private int taskStatus;

	@Column(name = "startdate", nullable = false)
	private Date taskStartDate;

	@Column(name = "enddate", nullable = false)
	private Date taskEndDate;

	@Column(name = "actualstartdate", nullable = false)
	private Date taskActualStartDate;

	@Column(name = "actualenddate", nullable = false)
	private Date taskActualEndDate;

	@Column(name = "companyid", nullable = false)
	private int companyId;

	@Column(name = "parent", nullable = true)
	private int taskParent;

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
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskDescription
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * @param taskDescription the taskDescription to set
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * @return the taskCreatedBy
	 */
	public int getTaskCreatedBy() {
		return taskCreatedBy;
	}

	/**
	 * @param taskCreatedBy the taskCreatedBy to set
	 */
	public void setTaskCreatedBy(int taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	/**
	 * @return the taskAssignedTo
	 */
	public int getTaskAssignedTo() {
		return taskAssignedTo;
	}

	/**
	 * @param taskAssignedTo the taskAssignedTo to set
	 */
	public void setTaskAssignedTo(int taskAssignedTo) {
		this.taskAssignedTo = taskAssignedTo;
	}

	/**
	 * @return the taskStatus
	 */
	public int getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus the taskStatus to set
	 */
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * @return the taskStartDate
	 */
	public Date getTaskStartDate() {
		return taskStartDate;
	}

	/**
	 * @param taskStartDate the taskStartDate to set
	 */
	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	/**
	 * @return the taskEndDate
	 */
	public Date getTaskEndDate() {
		return taskEndDate;
	}

	/**
	 * @param taskEndDate the taskEndDate to set
	 */
	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	/**
	 * @return the taskActualStartDate
	 */
	public Date getTaskActualStartDate() {
		return taskActualStartDate;
	}

	/**
	 * @param taskActualStartDate the taskActualStartDate to set
	 */
	public void setTaskActualStartDate(Date taskActualStartDate) {
		this.taskActualStartDate = taskActualStartDate;
	}

	/**
	 * @return the taskActualEndDate
	 */
	public Date getTaskActualEndDate() {
		return taskActualEndDate;
	}

	/**
	 * @param taskActualEndDate the taskActualEndDate to set
	 */
	public void setTaskActualEndDate(Date taskActualEndDate) {
		this.taskActualEndDate = taskActualEndDate;
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
	 * @return the taskParent
	 */
	public int getTaskParent() {
		return taskParent;
	}

	/**
	 * @param taskParent the taskParent to set
	 */
	public void setTaskParent(int taskParent) {
		this.taskParent = taskParent;
	}

	/**
	 * @param taskId
	 * @param taskName
	 * @param taskDescription
	 * @param taskCreatedBy
	 * @param taskAssignedTo
	 * @param taskStatus
	 * @param taskStartDate
	 * @param taskEndDate
	 * @param taskActualStartDate
	 * @param taskActualEndDate
	 * @param companyId
	 * @param taskParent
	 */
	public Task(int taskId, String taskName, String taskDescription, int taskCreatedBy, int taskAssignedTo,
			int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate, Date taskActualEndDate,
			int companyId, int taskParent) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskCreatedBy = taskCreatedBy;
		this.taskAssignedTo = taskAssignedTo;
		this.taskStatus = taskStatus;
		this.taskStartDate = taskStartDate;
		this.taskEndDate = taskEndDate;
		this.taskActualStartDate = taskActualStartDate;
		this.taskActualEndDate = taskActualEndDate;
		this.companyId = companyId;
		this.taskParent = taskParent;
	}

	/**
	 * 
	 */
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskCreatedBy=" + taskCreatedBy + ", taskAssignedTo=" + taskAssignedTo + ", taskStatus="
				+ taskStatus + ", taskStartDate=" + taskStartDate + ", taskEndDate=" + taskEndDate
				+ ", taskActualStartDate=" + taskActualStartDate + ", taskActualEndDate=" + taskActualEndDate
				+ ", companyId=" + companyId + ", taskParent=" + taskParent + "]";
	}

}
