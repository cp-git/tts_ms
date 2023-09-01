package com.cpa.ttsms.dto;

import java.util.List;

public class EmailDTO {
	private List<String> recipient;
	private String msgBody;
	private String subject;

	/**
	 * @return the recipient
	 */
	public List<String> getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(List<String> recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the msgBody
	 */
	public String getMsgBody() {
		return msgBody;
	}

	/**
	 * @param msgBody the msgBody to set
	 */
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param recipient
	 * @param msgBody
	 * @param subject
	 */
	public EmailDTO(List<String> recipient, String msgBody, String subject) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
	}

	/**
	 * 
	 */
	public EmailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Email [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject + "]";
	}
}
