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
@Table(name = "externaltask")
public class ExternalTask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "externalid")
	private int externalId;

	@Column(name = "candidatename")
	private String candidateName;

	@Column(name = "candidatecompany")
	private String candidateCompany; // Name of the company for the candidate

	@Column(name = "companyaddress")
	private String companyAddress; // Name of the company for the candidate

	@Column(name = "hrname")
	private String hrName; // Name of the candidate’s employer

	@Column(name = "hremail")
	private String hrEmail; // HR email address

	@Column(name = "hrphone")
	private String hrPhone; // HR phone number

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

	@Column(name = "taxtypeid")
	private int taxTypeId; // Foreign key of table "jobtype"

	@Column(name = "rate")
	private float rate; // $ per hour

	@Column(name = "recruitername")
	private String recruiterName;

	@Column(name = "recruiteremail")
	private String recruiterEmail;

	@Column(name = "recruiterphone")
	private String recruiterPhone;

	@Column(name = "jobsubmissionportalid")
	private int jobSubmissionPortalId; // Foreign key of portal id

	@Column(name = "portalname")
	private String portalName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dateposted")
	private Date datePosted;

	@Column(name = "joblink")
	private String jobLink;

	@Column(name = "visaid")
	private int visaId; // Visa Type, Foreign key of table "visatype"

	@Column(name = "taskid")
	private int taskId;

	@Column(name = "jobaddress")
	private String jobAddress;

	@Column(name = "jobcity")
	private String jobCity;

	@Column(name = "jobstate")
	private String jobState;

	@Column(name = "jobdescription")
	private String jobDescription;

	@Column(name = "candidateexperience")
	private int candidateExperience;

	@Column(name = "expectedmaxsal")
	private int expectedMaxSalary;

	@Column(name = "expectedminsal")
	private int expectedMinSalary;

	@Column(name = "iswillingtorelocate")
	private boolean willingToRelocate;

	@Column(name = "iswillingtonegotiatesal")
	private boolean willingToNegotiateSalary;

	/**
	 * 
	 */
	public ExternalTask() {
		super();
	}

	/**
	 * @param externalId
	 * @param candidateName
	 * @param candidateCompany
	 * @param companyAddress
	 * @param hrName
	 * @param hrEmail
	 * @param hrPhone
	 * @param jobPortalId
	 * @param hiringCompanyName
	 * @param jobTitle
	 * @param experienceRequired
	 * @param jobLocationId
	 * @param jobReferenceNumber
	 * @param taxTypeId
	 * @param rate
	 * @param recruiterName
	 * @param recruiterEmail
	 * @param recruiterPhone
	 * @param jobSubmissionPortalId
	 * @param portalName
	 * @param datePosted
	 * @param jobLink
	 * @param visaId
	 * @param taskId
	 * @param jobAddress
	 * @param jobCity
	 * @param jobState
	 * @param jobDescription
	 * @param candidateExperience
	 * @param expectedMaxSalary
	 * @param expectedMinSalary
	 * @param willingToRelocate
	 * @param willingToNegotiateSalary
	 */
	public ExternalTask(int externalId, String candidateName, String candidateCompany, String companyAddress,
			String hrName, String hrEmail, String hrPhone, int jobPortalId, String hiringCompanyName, String jobTitle,
			int experienceRequired, int jobLocationId, String jobReferenceNumber, int taxTypeId, float rate,
			String recruiterName, String recruiterEmail, String recruiterPhone, int jobSubmissionPortalId,
			String portalName, Date datePosted, String jobLink, int visaId, int taskId, String jobAddress,
			String jobCity, String jobState, String jobDescription, int candidateExperience, int expectedMaxSalary,
			int expectedMinSalary, boolean willingToRelocate, boolean willingToNegotiateSalary) {
		super();
		this.externalId = externalId;
		this.candidateName = candidateName;
		this.candidateCompany = candidateCompany;
		this.companyAddress = companyAddress;
		this.hrName = hrName;
		this.hrEmail = hrEmail;
		this.hrPhone = hrPhone;
		this.jobPortalId = jobPortalId;
		this.hiringCompanyName = hiringCompanyName;
		this.jobTitle = jobTitle;
		this.experienceRequired = experienceRequired;
		this.jobLocationId = jobLocationId;
		this.jobReferenceNumber = jobReferenceNumber;
		this.taxTypeId = taxTypeId;
		this.rate = rate;
		this.recruiterName = recruiterName;
		this.recruiterEmail = recruiterEmail;
		this.recruiterPhone = recruiterPhone;
		this.jobSubmissionPortalId = jobSubmissionPortalId;
		this.portalName = portalName;
		this.datePosted = datePosted;
		this.jobLink = jobLink;
		this.visaId = visaId;
		this.taskId = taskId;
		this.jobAddress = jobAddress;
		this.jobCity = jobCity;
		this.jobState = jobState;
		this.jobDescription = jobDescription;
		this.candidateExperience = candidateExperience;
		this.expectedMaxSalary = expectedMaxSalary;
		this.expectedMinSalary = expectedMinSalary;
		this.willingToRelocate = willingToRelocate;
		this.willingToNegotiateSalary = willingToNegotiateSalary;
	}

	/**
	 * @return the externalId
	 */
	public int getExternalId() {
		return externalId;
	}

	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}

	/**
	 * @return the candidateName
	 */
	public String getCandidateName() {
		return candidateName;
	}

	/**
	 * @param candidateName the candidateName to set
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	/**
	 * @return the candidateCompany
	 */
	public String getCandidateCompany() {
		return candidateCompany;
	}

	/**
	 * @param candidateCompany the candidateCompany to set
	 */
	public void setCandidateCompany(String candidateCompany) {
		this.candidateCompany = candidateCompany;
	}

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the hrName
	 */
	public String getHrName() {
		return hrName;
	}

	/**
	 * @param hrName the hrName to set
	 */
	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	/**
	 * @return the hrEmail
	 */
	public String getHrEmail() {
		return hrEmail;
	}

	/**
	 * @param hrEmail the hrEmail to set
	 */
	public void setHrEmail(String hrEmail) {
		this.hrEmail = hrEmail;
	}

	/**
	 * @return the hrPhone
	 */
	public String getHrPhone() {
		return hrPhone;
	}

	/**
	 * @param hrPhone the hrPhone to set
	 */
	public void setHrPhone(String hrPhone) {
		this.hrPhone = hrPhone;
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
	 * @return the taxTypeId
	 */
	public int getTaxTypeId() {
		return taxTypeId;
	}

	/**
	 * @param taxTypeId the taxTypeId to set
	 */
	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
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
	 * @return the recruiterName
	 */
	public String getRecruiterName() {
		return recruiterName;
	}

	/**
	 * @param recruiterName the recruiterName to set
	 */
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	/**
	 * @return the recruiterEmail
	 */
	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	/**
	 * @param recruiterEmail the recruiterEmail to set
	 */
	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}

	/**
	 * @return the recruiterPhone
	 */
	public String getRecruiterPhone() {
		return recruiterPhone;
	}

	/**
	 * @param recruiterPhone the recruiterPhone to set
	 */
	public void setRecruiterPhone(String recruiterPhone) {
		this.recruiterPhone = recruiterPhone;
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
	 * @return the visaId
	 */
	public int getVisaId() {
		return visaId;
	}

	/**
	 * @param visaId the visaId to set
	 */
	public void setVisaId(int visaId) {
		this.visaId = visaId;
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
	 * @return the candidateExperience
	 */
	public int getCandidateExperience() {
		return candidateExperience;
	}

	/**
	 * @param candidateExperience the candidateExperience to set
	 */
	public void setCandidateExperience(int candidateExperience) {
		this.candidateExperience = candidateExperience;
	}

	/**
	 * @return the expectedMaxSalary
	 */
	public int getExpectedMaxSalary() {
		return expectedMaxSalary;
	}

	/**
	 * @param expectedMaxSalary the expectedMaxSalary to set
	 */
	public void setExpectedMaxSalary(int expectedMaxSalary) {
		this.expectedMaxSalary = expectedMaxSalary;
	}

	/**
	 * @return the expectedMinSalary
	 */
	public int getExpectedMinSalary() {
		return expectedMinSalary;
	}

	/**
	 * @param expectedMinSalary the expectedMinSalary to set
	 */
	public void setExpectedMinSalary(int expectedMinSalary) {
		this.expectedMinSalary = expectedMinSalary;
	}

	/**
	 * @return the willingToRelocate
	 */
	public boolean isWillingToRelocate() {
		return willingToRelocate;
	}

	/**
	 * @param willingToRelocate the willingToRelocate to set
	 */
	public void setWillingToRelocate(boolean willingToRelocate) {
		this.willingToRelocate = willingToRelocate;
	}

	/**
	 * @return the willingToNegotiateSalary
	 */
	public boolean isWillingToNegotiateSalary() {
		return willingToNegotiateSalary;
	}

	/**
	 * @param willingToNegotiateSalary the willingToNegotiateSalary to set
	 */
	public void setWillingToNegotiateSalary(boolean willingToNegotiateSalary) {
		this.willingToNegotiateSalary = willingToNegotiateSalary;
	}

}
