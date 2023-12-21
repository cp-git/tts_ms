package com.cpa.ttsms.dto;

import java.util.Date;

import com.cpa.ttsms.entity.ExternalTask;
import com.cpa.ttsms.entity.InternalTask;
import com.cpa.ttsms.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;

public class InternalExternalTaskDTO {

	private int taskId;

	private String taskName;

	private String taskDescription;

	private int taskCreatedBy;

	private int taskAssignedTo;

	private int taskStatus;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskEndDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskActualStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date taskActualEndDate;

	private int companyId;

	private int taskParent;

	private String reason;

	private int employeeId;

	private String fileName;

	private boolean havingChild;

	private int internalId;

	private int externalId; // Primary Key

	private int jobPortalId; // Foreign key of table "jobportal"

	private String hiringCompanyName; // Name of the company who advertised for a job

	private String jobTitle; // Job Title

	private int experienceRequired; // Indicates the number of years of experience needed for the job

	private int jobLocationId; // Foreign key of table "joblocation"

	private String jobReferenceNumber;

	private int taxTypeId; // Foreign key of table "jobtype"

	private float rate; // $ per hour

	private String recruiterName;

	private String recruiterEmail;

	private String recruiterPhone;

	private int jobSubmissionPortalId; // Foreign key of portal id

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datePosted;

	private String jobLink;

	private int candidateId;

	private String candidateName; // Name of the candidate we are hiring or the person who is on the bench

	private String candidateCompany; // Name of the company for the candidate

	private String companyAddress;

	private String hrName; // Name of the candidate’s employer

	private String hrEmail; // HR email address

	private String hrPhone; // HR phone number

	private int visaId; // Visa Type, Foreign key of table "visatype"

	private int placementId;

	/**
	 * 
	 */
	public InternalExternalTaskDTO() {
		super();
	}

	public InternalExternalTaskDTO(Task task, InternalTask internalTask) {
		this.taskId = task.getTaskId();
		this.taskName = task.getTaskName();
		this.taskDescription = task.getTaskDescription();
		this.taskCreatedBy = task.getTaskCreatedBy();
		this.taskAssignedTo = task.getTaskAssignedTo();
		this.taskStatus = task.getTaskStatus();
		this.taskStartDate = task.getTaskStartDate();
		this.taskEndDate = task.getTaskEndDate();
		this.taskActualStartDate = task.getTaskActualStartDate();
		this.taskActualEndDate = task.getTaskActualEndDate();
		this.companyId = task.getCompanyId();
		this.taskParent = task.getTaskParent();
		this.havingChild = task.isHavingChild();
		this.placementId = task.getPlacementId();

		this.internalId = internalTask.getInternalId();
		this.candidateId = internalTask.getCandidateId();
		this.jobPortalId = internalTask.getJobPortalId();
		this.hiringCompanyName = internalTask.getHiringCompanyName();
		this.jobTitle = internalTask.getJobTitle();
		this.experienceRequired = internalTask.getExperienceRequired();
		this.jobLocationId = internalTask.getJobLocationId();
		this.jobReferenceNumber = internalTask.getJobReferenceNumber();
		this.taxTypeId = internalTask.getTaxTypeId();
		this.rate = internalTask.getRate();
		this.recruiterName = internalTask.getRecruiterName();
		this.recruiterEmail = internalTask.getRecruiterEmail();
		this.recruiterPhone = internalTask.getRecruiterPhone();
		this.jobSubmissionPortalId = internalTask.getJobSubmissionPortalId();
		this.datePosted = internalTask.getDatePosted();
		this.jobLink = internalTask.getJobLink();
		this.visaId = internalTask.getVisaId();
	}

	public InternalExternalTaskDTO(Task task, ExternalTask externalTask) {
		this.taskId = task.getTaskId();
		this.taskName = task.getTaskName();
		this.taskDescription = task.getTaskDescription();
		this.taskCreatedBy = task.getTaskCreatedBy();
		this.taskAssignedTo = task.getTaskAssignedTo();
		this.taskStatus = task.getTaskStatus();
		this.taskStartDate = task.getTaskStartDate();
		this.taskEndDate = task.getTaskEndDate();
		this.taskActualStartDate = task.getTaskActualStartDate();
		this.taskActualEndDate = task.getTaskActualEndDate();
		this.companyId = task.getCompanyId();
		this.taskParent = task.getTaskParent();
		this.havingChild = task.isHavingChild();
		this.placementId = task.getPlacementId();

		this.externalId = externalTask.getExternalId();
		this.candidateName = externalTask.getCandidateName();
		this.candidateCompany = externalTask.getCandidateCompany();
		this.companyAddress = externalTask.getCompanyAddress();
		this.hrName = externalTask.getHrName();
		this.hrEmail = externalTask.getHrEmail();
		this.hrPhone = externalTask.getHrPhone();

		this.jobPortalId = externalTask.getJobPortalId();
		this.hiringCompanyName = externalTask.getHiringCompanyName();
		this.jobTitle = externalTask.getJobTitle();
		this.experienceRequired = externalTask.getExperienceRequired();
		this.jobLocationId = externalTask.getJobLocationId();
		this.jobReferenceNumber = externalTask.getJobReferenceNumber();
		this.taxTypeId = externalTask.getTaxTypeId();
		this.rate = externalTask.getRate();
		this.recruiterName = externalTask.getRecruiterName();
		this.recruiterEmail = externalTask.getRecruiterEmail();
		this.recruiterPhone = externalTask.getRecruiterPhone();
		this.jobSubmissionPortalId = externalTask.getJobSubmissionPortalId();
		this.datePosted = externalTask.getDatePosted();
		this.jobLink = externalTask.getJobLink();
		this.visaId = externalTask.getVisaId();
	}

	/**
	 * @param taskId
	 * @param taskName
	 * @param taskDescription
	 * @param taskCreatedBy
	 * @param taskAssignedTo
	 * @param taskStatus
	 * @param taskStartDate
	 * @param taskEndDate
	 * @param taskActualStartDate
	 * @param taskActualEndDate
	 * @param companyId
	 * @param taskParent
	 * @param reason
	 * @param employeeId
	 * @param fileName
	 * @param havingChild
	 * @param internalId
	 * @param externalId
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
	 * @param datePosted
	 * @param jobLink
	 * @param candidateName
	 * @param candidateCompany
	 * @param hrName
	 * @param hrEmail
	 * @param hrPhone
	 * @param visaId
	 */
	public InternalExternalTaskDTO(int taskId, String taskName, String taskDescription, int taskCreatedBy,
			int taskAssignedTo, int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate,
			Date taskActualEndDate, int companyId, int taskParent, String reason, int employeeId, String fileName,
			boolean havingChild, int internalId, int externalId, int jobPortalId, String hiringCompanyName,
			String jobTitle, int experienceRequired, int jobLocationId, String jobReferenceNumber, int taxTypeId,
			float rate, String recruiterName, String recruiterEmail, String recruiterPhone, int jobSubmissionPortalId,
			Date datePosted, String jobLink, String candidateName, String candidateCompany, String companyAddress,
			String hrName, String hrEmail, String hrPhone, int visaId, int placementId) {
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
		this.reason = reason;
		this.employeeId = employeeId;
		this.fileName = fileName;
		this.havingChild = havingChild;
		this.internalId = internalId;
		this.externalId = externalId;
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
		this.datePosted = datePosted;
		this.jobLink = jobLink;
		this.candidateName = candidateName;
		this.candidateCompany = candidateCompany;
		this.companyAddress = companyAddress;
		this.hrName = hrName;
		this.hrEmail = hrEmail;
		this.hrPhone = hrPhone;
		this.visaId = visaId;
		this.placementId = placementId;
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
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskDescription
	 */
	public String getTaskDescription() {
		return taskDescription;
	}

	/**
	 * @param taskDescription the taskDescription to set
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * @return the taskCreatedBy
	 */
	public int getTaskCreatedBy() {
		return taskCreatedBy;
	}

	/**
	 * @param taskCreatedBy the taskCreatedBy to set
	 */
	public void setTaskCreatedBy(int taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	/**
	 * @return the taskAssignedTo
	 */
	public int getTaskAssignedTo() {
		return taskAssignedTo;
	}

	/**
	 * @param taskAssignedTo the taskAssignedTo to set
	 */
	public void setTaskAssignedTo(int taskAssignedTo) {
		this.taskAssignedTo = taskAssignedTo;
	}

	/**
	 * @return the taskStatus
	 */
	public int getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus the taskStatus to set
	 */
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * @return the taskStartDate
	 */
	public Date getTaskStartDate() {
		return taskStartDate;
	}

	/**
	 * @param taskStartDate the taskStartDate to set
	 */
	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	/**
	 * @return the taskEndDate
	 */
	public Date getTaskEndDate() {
		return taskEndDate;
	}

	/**
	 * @param taskEndDate the taskEndDate to set
	 */
	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	/**
	 * @return the taskActualStartDate
	 */
	public Date getTaskActualStartDate() {
		return taskActualStartDate;
	}

	/**
	 * @param taskActualStartDate the taskActualStartDate to set
	 */
	public void setTaskActualStartDate(Date taskActualStartDate) {
		this.taskActualStartDate = taskActualStartDate;
	}

	/**
	 * @return the taskActualEndDate
	 */
	public Date getTaskActualEndDate() {
		return taskActualEndDate;
	}

	/**
	 * @param taskActualEndDate the taskActualEndDate to set
	 */
	public void setTaskActualEndDate(Date taskActualEndDate) {
		this.taskActualEndDate = taskActualEndDate;
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
	 * @return the taskParent
	 */
	public int getTaskParent() {
		return taskParent;
	}

	/**
	 * @param taskParent the taskParent to set
	 */
	public void setTaskParent(int taskParent) {
		this.taskParent = taskParent;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	 * @return the havingChild
	 */
	public boolean isHavingChild() {
		return havingChild;
	}

	/**
	 * @param havingChild the havingChild to set
	 */
	public void setHavingChild(boolean havingChild) {
		this.havingChild = havingChild;
	}

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
	 * @param candidateCompany the candidateCompany to set
	 */
	public void setCandidateCompany(String candidateCompany) {
		this.candidateCompany = candidateCompany;
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
	 * @return the placementId
	 */
	public int getPlacementId() {
		return placementId;
	}

	/**
	 * @param placementId the placementId to set
	 */
	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	@Override
	public String toString() {
		return "InternalExternalTaskDTO [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", taskCreatedBy=" + taskCreatedBy + ", taskAssignedTo=" + taskAssignedTo
				+ ", taskStatus=" + taskStatus + ", taskStartDate=" + taskStartDate + ", taskEndDate=" + taskEndDate
				+ ", taskActualStartDate=" + taskActualStartDate + ", taskActualEndDate=" + taskActualEndDate
				+ ", companyId=" + companyId + ", taskParent=" + taskParent + ", reason=" + reason + ", employeeId="
				+ employeeId + ", fileName=" + fileName + ", havingChild=" + havingChild + ", internalId=" + internalId
				+ ", externalId=" + externalId + ", jobPortalId=" + jobPortalId + ", hiringCompanyName="
				+ hiringCompanyName + ", jobTitle=" + jobTitle + ", experienceRequired=" + experienceRequired
				+ ", jobLocationId=" + jobLocationId + ", jobReferenceNumber=" + jobReferenceNumber + ", taxTypeId="
				+ taxTypeId + ", rate=" + rate + ", recruiterName=" + recruiterName + ", recruiterEmail="
				+ recruiterEmail + ", recruiterPhone=" + recruiterPhone + ", jobSubmissionPortalId="
				+ jobSubmissionPortalId + ", datePosted=" + datePosted + ", jobLink=" + jobLink + ", candidateId="
				+ candidateId + ", candidateName=" + candidateName + ", candidateCompany=" + candidateCompany
				+ ", companyAddress=" + companyAddress + ", hrName=" + hrName + ", hrEmail=" + hrEmail + ", hrPhone="
				+ hrPhone + ", visaId=" + visaId + ", placementId=" + placementId + "]";
	}

}
