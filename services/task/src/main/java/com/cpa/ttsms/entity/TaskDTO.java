/**
 * This class represents a Data Transfer Object (DTO) for Task entities. It is used
 * for transferring task-related data between the controller and service layers.
 * The TaskDTO contains information such as the username of the user performing the
 * update the task ID, status, and the ID of the user to whom the task is assigned.
 */
package com.cpa.ttsms.entity;

public class TaskDTO {

	private String username;
	private int empid;
	private int taskId;
	private int status;
	private int assignedTo;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the empid
	 */
	public int getEmpid() {
		return empid;
	}

	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(int empid) {
		this.empid = empid;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
	 * @param username
	 * @param taskId
	 * @param status
	 * @param assignedTo
	 */
	public TaskDTO(String username, int taskId, int status, int assignedTo) {
		super();
		this.username = username;
		this.taskId = taskId;
		this.status = status;
		this.assignedTo = assignedTo;
	}

	/**
	 * 
	 */
	public TaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PasswordTaskDTO [username=" + username + ", taskId=" + taskId + ", status=" + status + ", assignedTo="
				+ assignedTo + "]";
	}

}
