/**
 * @author  - Code Generator
 * @createdOn -  17/01/2024
 * @Description Entity class for hiringcompany
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
@Table(name = "hiringcompany")
public class HiringCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hiringcompanyid")
	private int hiringCompanyId;

	@Column(name = "hiringcompanyname")
	private String hiringCompanyName;

	@Column(name = "jobtitle")
	private String jobTitle;

	@Column(name = "jobmode")
	private int jobMode;

	@Column(name = "jobaddress")
	private String jobAddress;

	@Column(name = "jobcity")
	private String jobCity;

	@Column(name = "jobstatue")
	private String jobState;

	@Column(name = "exprequired")
	private int experienceRequired;

	@Column(name = "rate")
	private int rate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "jobfulfillmentdate")
	private Date jobFulfillmentDate;

	@Column(name = "jobsubmissionmethod")
	private int jobSubmissionMethod;

	@Column(name = "jobdescription", length = 1024)
	private String jobDescription;

	@Column(name = "companyid")
	private int companyId;

	@Column(name = "portalName")
	private String portalName;
	
	
	@Column(name = "userActive")
	private boolean userActive;
	
	

	/**
	 * @return the hiringCompanyId
	 */
	public int getHiringCompanyId() {
		return hiringCompanyId;
	}

	/**
	 * @param hiringCompanyId the hiringCompanyId to set
	 */
	public void setHiringCompanyId(int hiringCompanyId) {
		this.hiringCompanyId = hiringCompanyId;
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
	 * @return the jobMode
	 */
	public int getJobMode() {
		return jobMode;
	}

	/**
	 * @param jobMode the jobMode to set
	 */
	public void setJobMode(int jobMode) {
		this.jobMode = jobMode;
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
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	/**
	 * @return the jobFulfillmentDate
	 */
	public Date getJobFulfillmentDate() {
		return jobFulfillmentDate;
	}

	/**
	 * @param jobFulfillmentDate the jobFulfillmentDate to set
	 */
	public void setJobFulfillmentDate(Date jobFulfillmentDate) {
		this.jobFulfillmentDate = jobFulfillmentDate;
	}

	/**
	 * @return the jobSubmissionMethod
	 */
	public int getJobSubmissionMethod() {
		return jobSubmissionMethod;
	}

	/**
	 * @param jobSubmissionMethod the jobSubmissionMethod to set
	 */
	public void setJobSubmissionMethod(int jobSubmissionMethod) {
		this.jobSubmissionMethod = jobSubmissionMethod;
	}

	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}

	/**
	 * @param jobDescription the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
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
	 * @return the portalName
	 */
	public String getPortalName() {
		return portalName;
	}

	/**
	 * @param portalName the portalName to set
	 */
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}
	
	

}
