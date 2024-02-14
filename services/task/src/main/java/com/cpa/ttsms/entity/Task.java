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

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "startdate", nullable = false)
	private Date taskStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "enddate", nullable = false)
	private Date taskEndDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "actualstartdate")
	private Date taskActualStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "actualenddate")
	private Date taskActualEndDate;

	@Column(name = "companyid", nullable = false)
	private int companyId;

	@Column(name = "parent", nullable = true)
	private int taskParent;

	@Column(name = "havingchild", nullable = true)
	private boolean havingChild;

	@Column(name = "placementid", nullable = false)
	private int placementId;
	
	@Column (name="taskchangedate")
	private Date taskChangeDate;

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

	public Task(int taskId, String taskName, String taskDescription, int taskCreatedBy, int taskAssignedTo,
			int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate, Date taskActualEndDate,
			int companyId, int taskParent, boolean havingChild, int placementId, Date taskChangeDate) {
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
	}

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
				+ ", companyId=" + companyId + ", taskParent=" + taskParent + ", havingChild=" + havingChild
				+ ", placementId=" + placementId + ", taskChangeDate=" + taskChangeDate + "]";
	}

	

}
