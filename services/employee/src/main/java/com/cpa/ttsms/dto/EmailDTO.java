package com.cpa.ttsms.dto;

public class EmailDTO {

	// Class data members
	private String recipient;
	private String msgBody;
	private String subject;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "EmailDTO [recipient=" + recipient + ", msgBody=" + msgBody + ", subject=" + subject + "]";
	}

	public EmailDTO(String recipient, String msgBody, String subject) {
		super();
		this.recipient = recipient;
		this.msgBody = msgBody;
		this.subject = subject;
	}

	public EmailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
