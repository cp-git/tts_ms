package com.cpa.ttsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taskattachment")
public class TaskAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int attachmentId;

	@Column(name = "taskid")
	private int taskID;

	@Column(name = "filename")
	private String fileName;

	@Column(name = "attachedby")
	private int attachedBy;

	/**
	 * 
	 */
	public TaskAttachment() {
		super();
	}

	/**
	 * @param attachmentId
	 * @param taskID
	 * @param fileName
	 * @param attachedBy
	 */
	public TaskAttachment(int attachmentId, int taskID, String fileName, int attachedBy) {
		super();
		this.attachmentId = attachmentId;
		this.taskID = taskID;
		this.fileName = fileName;
		this.attachedBy = attachedBy;
	}

	/**
	 * @return the attachmentId
	 */
	public int getAttachmentId() {
		return attachmentId;
	}

	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	/**
	 * @return the taskID
	 */
	public int getTaskID() {
		return taskID;
	}

	/**
	 * @param taskID the taskID to set
	 */
	public void setTaskID(int taskID) {
		this.taskID = taskID;
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
	 * @return the attachedBy
	 */
	public int getAttachedBy() {
		return attachedBy;
	}

	/**
	 * @param attachedBy the attachedBy to set
	 */
	public void setAttachedBy(int attachedBy) {
		this.attachedBy = attachedBy;
	}

}
