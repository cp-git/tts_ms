/**
 * @author  - Code Generator
 * @createdOn -  25-07-2023
 * @Description Entity class for Email
 * 
 */

package com.cpa.ttsms.entity;

public class Email {

	// Class data members
	private String recipient;
	private String msgBody;
	private String subject;

	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(String recipient) {
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
	public Email(String recipient, String msgBody, String subject) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
	}

	/**
	 * 
	 */
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Email [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject + "]";
	}

}
