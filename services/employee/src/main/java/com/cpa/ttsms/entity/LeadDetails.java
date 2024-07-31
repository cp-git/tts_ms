package com.cpa.ttsms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "leaddetails")
public class LeadDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "userid")
	private int userId;

	@Column(name = "companyname")
	private String companyName;

	@Column(name = "recruitername")
	private String recruiterName;

	@Column(name = "joblocation")
	private String jobLocation;

	@Column(name = "positionname")
	private String positionName;

	@Column(name = "recruiternumber")
	private String recruiterNumber;

	@Column(name = "recruitermail")
	private String recruiterMail;

	@Column(name = "link")
	private String link;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "status")
	private String status;

	@Column(name = "comment")
	private String comment;

	//Constructors
	public LeadDetails(int id, int userId, String companyName, String recruiterName, String jobLocation,
			String positionName, String recruiterNumber, String recruiterMail, String link, LocalDate date,
			String status, String comment) {
		super();
		this.id = id;
		this.userId = userId;
		this.companyName = companyName;
		this.recruiterName = recruiterName;
		this.jobLocation = jobLocation;
		this.positionName = positionName;
		this.recruiterNumber = recruiterNumber;
		this.recruiterMail = recruiterMail;
		this.link = link;
		this.date = date;
		this.status = status;
		this.comment = comment;
	}

	/**
	 * 
	 */
	public LeadDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getRecruiterNumber() {
		return recruiterNumber;
	}

	public void setRecruiterNumber(String recruiterNumber) {
		this.recruiterNumber = recruiterNumber;
	}

	public String getRecruiterMail() {
		return recruiterMail;
	}

	public void setRecruiterMail(String recruiterMail) {
		this.recruiterMail = recruiterMail;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "LeadDetails [id=" + id + ", userId=" + userId + ", companyName=" + companyName + ", recruiterName="
				+ recruiterName + ", jobLocation=" + jobLocation + ", positionName=" + positionName
				+ ", recruiterNumber=" + recruiterNumber + ", recruiterMail=" + recruiterMail + ", link=" + link
				+ ", date=" + date + ", status=" + status + ", comment=" + comment + "]";
	}
	
	
	
	
	

	
	
	

}
