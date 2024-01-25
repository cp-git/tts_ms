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
@Table(name = "internaltask")
public class InternalTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "internalid")
	private int internalId;

	@Column(name = "benchcandidateid")
	private int benchCandidateId;

	@Column(name = "jobportalid")
	private int jobPortalId; // Foreign key of table "jobportal"

	@Column(name = "hiringcompanyname")
	private String hiringCompanyName; // Name of the company who advertised for a job

	@Column(name = "jobtitle")
	private String jobTitle; // Job Title

	@Column(name = "experiencerequired")
	private int experienceRequired; // Indicates the number of years of experience needed for the job

	@Column(name = "joblocationid")
	private int jobLocationId; // Foreign key of table "joblocation"

	@Column(name = "jobreferencenumber")
	private String jobReferenceNumber;

	@Column(name = "rate")
	private float rate; // $ per hour

	@Column(name = "vendorname")
	private String vendorName;

	@Column(name = "vendoremail")
	private String vendorEmail;

	@Column(name = "vendorphone")
	private String vendorPhone;

	@Column(name = "jobsubmissionportalid")
	private int jobSubmissionPortalId; // Foreign key of portal id

	@Column(name = "portalname")
	private String portalName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dateposted")
	private Date datePosted;

	@Column(name = "joblink")
	private String jobLink;

	@Column(name = "taskid")
	private int taskId;

	@Column(name = "jobaddress")
	private String jobAddress;

	@Column(name = "jobcity")
	private String jobCity;

	@Column(name = "jobstate")
	private String jobState;

	@Column(name = "commentoncandidate")
	private String commentOnCandidate;

	/**
	 * @return the internalId
	 */
	public int getInternalId() {
		return internalId;
	}

	/**
	 * @param internalId the internalId to set
	 */
	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	/**
	 * @return the benchCandidateId
	 */
	public int getBenchCandidateId() {
		return benchCandidateId;
	}

	/**
	 * @param benchCandidateId the benchCandidateId to set
	 */
	public void setBenchCandidateId(int benchCandidateId) {
		this.benchCandidateId = benchCandidateId;
	}

	/**
	 * @return the jobPortalId
	 */
	public int getJobPortalId() {
		return jobPortalId;
	}

	/**
	 * @param jobPortalId the jobPortalId to set
	 */
	public void setJobPortalId(int jobPortalId) {
		this.jobPortalId = jobPortalId;
	}

	/**
	 * @return the hiringCompanyName
	 */
	public String getHiringCompanyName() {
		return hiringCompanyName;
	}

	/**
	 * @param hiringCompanyName the hiringCompanyName to set
	 */
	public void setHiringCompanyName(String hiringCompanyName) {
		this.hiringCompanyName = hiringCompanyName;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the experienceRequired
	 */
	public int getExperienceRequired() {
		return experienceRequired;
	}

	/**
	 * @param experienceRequired the experienceRequired to set
	 */
	public void setExperienceRequired(int experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	/**
	 * @return the jobLocationId
	 */
	public int getJobLocationId() {
		return jobLocationId;
	}

	/**
	 * @param jobLocationId the jobLocationId to set
	 */
	public void setJobLocationId(int jobLocationId) {
		this.jobLocationId = jobLocationId;
	}

	/**
	 * @return the jobReferenceNumber
	 */
	public String getJobReferenceNumber() {
		return jobReferenceNumber;
	}

	/**
	 * @param jobReferenceNumber the jobReferenceNumber to set
	 */
	public void setJobReferenceNumber(String jobReferenceNumber) {
		this.jobReferenceNumber = jobReferenceNumber;
	}

	/**
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}

	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}

	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	/**
	 * @return the vendorEmail
	 */
	public String getVendorEmail() {
		return vendorEmail;
	}

	/**
	 * @param vendorEmail the vendorEmail to set
	 */
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	/**
	 * @return the vendorPhone
	 */
	public String getVendorPhone() {
		return vendorPhone;
	}

	/**
	 * @param vendorPhone the vendorPhone to set
	 */
	public void setVendorPhone(String vendorPhone) {
		this.vendorPhone = vendorPhone;
	}

	/**
	 * @return the jobSubmissionPortalId
	 */
	public int getJobSubmissionPortalId() {
		return jobSubmissionPortalId;
	}

	/**
	 * @param jobSubmissionPortalId the jobSubmissionPortalId to set
	 */
	public void setJobSubmissionPortalId(int jobSubmissionPortalId) {
		this.jobSubmissionPortalId = jobSubmissionPortalId;
	}

	/**
	 * @return the datePosted
	 */
	public Date getDatePosted() {
		return datePosted;
	}

	/**
	 * @param datePosted the datePosted to set
	 */
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	/**
	 * @return the jobLink
	 */
	public String getJobLink() {
		return jobLink;
	}

	/**
	 * @param jobLink the jobLink to set
	 */
	public void setJobLink(String jobLink) {
		this.jobLink = jobLink;
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

	public String getPortalName() {
		return portalName;
	}

	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	/**
	 * @return the jobAddress
	 */
	public String getJobAddress() {
		return jobAddress;
	}

	/**
	 * @param jobAddress the jobAddress to set
	 */
	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	/**
	 * @return the jobCity
	 */
	public String getJobCity() {
		return jobCity;
	}

	/**
	 * @param jobCity the jobCity to set
	 */
	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}

	/**
	 * @return the jobState
	 */
	public String getJobState() {
		return jobState;
	}

	/**
	 * @param jobState the jobState to set
	 */
	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	/**
	 * @return the commentOnCandidate
	 */
	public String getCommentOnCandidate() {
		return commentOnCandidate;
	}

	/**
	 * @param commentOnCandidate the commentOnCandidate to set
	 */
	public void setCommentOnCandidate(String commentOnCandidate) {
		this.commentOnCandidate = commentOnCandidate;
	}

}
