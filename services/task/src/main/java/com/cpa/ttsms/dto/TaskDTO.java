package com.cpa.ttsms.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskDTO {

	private int taskId;

	private String taskName;

	private String taskDescription;

	private int taskCreatedBy;

	private int taskAssignedTo;

	private int taskStatus;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskEndDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskActualStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskActualEndDate;

	private int companyId;

	private int taskParent;

	private boolean havingChild;

	private int placementId;

	private Date taskChangeDate;

	private int benchCandidateId;

	private int hiringCompanyId;

	public TaskDTO() {
		super();
	}

	public TaskDTO(int taskId, String taskName, String taskDescription, int taskCreatedBy, int taskAssignedTo,
			int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate, Date taskActualEndDate,
			int companyId, int taskParent, boolean havingChild, int placementId, Date taskChangeDate,
			int benchCandidateId, int hiringCompanyId) {
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
		this.havingChild = havingChild;
		this.placementId = placementId;
		this.taskChangeDate = taskChangeDate;
		this.benchCandidateId = benchCandidateId;
		this.hiringCompanyId = hiringCompanyId;
	}

	public TaskDTO(InternalExternalTaskDTO intExtParentTask) {
		this.taskId = intExtParentTask.getTaskId();
		this.taskName = intExtParentTask.getTaskName();
		this.taskDescription = intExtParentTask.getTaskDescription();
		this.taskCreatedBy = intExtParentTask.getTaskCreatedBy();
		this.taskAssignedTo = intExtParentTask.getTaskAssignedTo();
		this.taskStatus = intExtParentTask.getTaskStatus();
		this.taskStartDate = intExtParentTask.getTaskStartDate();
		this.taskEndDate = intExtParentTask.getTaskEndDate();
		this.taskActualStartDate = intExtParentTask.getTaskActualStartDate();
		this.taskActualEndDate = intExtParentTask.getTaskActualEndDate();
		this.companyId = intExtParentTask.getCompanyId();
		this.taskParent = intExtParentTask.getTaskParent();
		this.havingChild = intExtParentTask.isHavingChild();
		this.placementId = intExtParentTask.getPlacementId();
		this.taskChangeDate = intExtParentTask.getTaskChangeDate();
		this.benchCandidateId = intExtParentTask.getBenchCandidateId();
		this.hiringCompanyId = intExtParentTask.getHiringCompanyId();
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public int getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(int taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	public int getTaskAssignedTo() {
		return taskAssignedTo;
	}

	public void setTaskAssignedTo(int taskAssignedTo) {
		this.taskAssignedTo = taskAssignedTo;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public Date getTaskActualStartDate() {
		return taskActualStartDate;
	}

	public void setTaskActualStartDate(Date taskActualStartDate) {
		this.taskActualStartDate = taskActualStartDate;
	}

	public Date getTaskActualEndDate() {
		return taskActualEndDate;
	}

	public void setTaskActualEndDate(Date taskActualEndDate) {
		this.taskActualEndDate = taskActualEndDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getTaskParent() {
		return taskParent;
	}

	public void setTaskParent(int taskParent) {
		this.taskParent = taskParent;
	}

	public boolean isHavingChild() {
		return havingChild;
	}

	public void setHavingChild(boolean havingChild) {
		this.havingChild = havingChild;
	}

	public int getPlacementId() {
		return placementId;
	}

	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}

	public Date getTaskChangeDate() {
		return taskChangeDate;
	}

	public void setTaskChangeDate(Date taskChangeDate) {
		this.taskChangeDate = taskChangeDate;
	}

	public int getBenchCandidateId() {
		return benchCandidateId;
	}

	public void setBenchCandidateId(int benchCandidateId) {
		this.benchCandidateId = benchCandidateId;
	}

	public int getHiringCompanyId() {
		return hiringCompanyId;
	}

	public void setHiringCompanyId(int hiringCompanyId) {
		this.hiringCompanyId = hiringCompanyId;
	}

	@Override
	public String toString() {
		return "TaskDTO [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskCreatedBy=" + taskCreatedBy + ", taskAssignedTo=" + taskAssignedTo + ", taskStatus="
				+ taskStatus + ", taskStartDate=" + taskStartDate + ", taskEndDate=" + taskEndDate
				+ ", taskActualStartDate=" + taskActualStartDate + ", taskActualEndDate=" + taskActualEndDate
				+ ", companyId=" + companyId + ", taskParent=" + taskParent + ", havingChild=" + havingChild
				+ ", placementId=" + placementId + ", taskChangeDate=" + taskChangeDate + ", benchCandidateId="
				+ benchCandidateId + ", hiringCompanyId=" + hiringCompanyId + "]";
	}

}
