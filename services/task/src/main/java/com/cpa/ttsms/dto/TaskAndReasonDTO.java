package com.cpa.ttsms.dto;

import java.util.Date;

public class TaskAndReasonDTO {

	private int taskId;

	private String taskName;

	private String taskDescription;

	private int taskCreatedBy;

	private int taskAssignedTo;

	private int taskStatus;

	private Date taskStartDate;

	private Date taskEndDate;

	private Date taskActualStartDate;

	private Date taskActualEndDate;

	private int companyId;

	private int taskParent;

	private String reason;

	private int employeeId;

	private String fileName;

	private boolean havingChild;
	
	private Date taskChangeDate;

	public Date getTaskChangeDate() {
		return taskChangeDate;
	}

	public void setTaskChangeDate(Date taskChangeDate) {
		this.taskChangeDate = taskChangeDate;
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
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the havingChild
	 */
	public boolean isHavingChild() {
		return havingChild;
	}

	/**
	 * @param havingChild the havingChild to set
	 */
	public void setHavingChild(boolean havingChild) {
		this.havingChild = havingChild;
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
	 * @param reason
	 * @param employeeId
	 * @param fileName
	 * @param havingChild
	 */

	public TaskAndReasonDTO(int taskId, String taskName, String taskDescription, int taskCreatedBy, int taskAssignedTo,
			int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate, Date taskActualEndDate,
			int companyId, int taskParent, String reason, int employeeId, String fileName, boolean havingChild,
			Date taskChangeDate) {
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
		this.reason = reason;
		this.employeeId = employeeId;
		this.fileName = fileName;
		this.havingChild = havingChild;
		this.taskChangeDate = taskChangeDate;
	}

	
	public TaskAndReasonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TaskAndReasonDTO [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskCreatedBy=" + taskCreatedBy + ", taskAssignedTo=" + taskAssignedTo + ", taskStatus="
				+ taskStatus + ", taskStartDate=" + taskStartDate + ", taskEndDate=" + taskEndDate
				+ ", taskActualStartDate=" + taskActualStartDate + ", taskActualEndDate=" + taskActualEndDate
				+ ", companyId=" + companyId + ", taskParent=" + taskParent + ", reason=" + reason + ", employeeId="
				+ employeeId + ", fileName=" + fileName + ", havingChild=" + havingChild + ", taskChangeDate="
				+ taskChangeDate + "]";
	}

	

}
