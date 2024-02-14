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
	private boolean havingChild;
	private int placementId;
	private Date taskChangeDate;
	private String reason;
	private int employeeId;

	// Internal
	private int internalId;
	private String hiringCompanyName;
	private String jobTitle;
	private int jobLocationId;
	private String jobAddress;
	private String jobCity;
	private String jobState;
	private int experienceRequired;
	private float rate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datePosted;
	private String jobLink;
	private int jobPortalId;
	private String jobReferenceNumber;
	private int taxTypeId;
	private String recruiterName;
	private String recruiterEmail;
	private String recruiterPhone;
	private int jobSubmissionPortalId;
	private String portalName;
	private String commentOnCandidate;
	private int minBillingRate;

	// External
	private int externalId;
	private String candidateName;
	private String candidateCompany;
	private String companyAddress;
	private int visaId;
	private int candidateExperience;
	private int expectedMaxSalary;
	private int expectedMinSalary;
	private boolean willingToRelocate;
	private boolean willingToNegotiateSalary;
	private String reasonToFitForJob;

	// Add new fields from Angular class
	private int hiringCompanyId;
	private int benchCandidateId;

	/**
	 * 
	 */
	public InternalExternalTaskDTO() {
		super();
	}

	public InternalExternalTaskDTO(int taskId, String taskName, String taskDescription, int taskCreatedBy,
			int taskAssignedTo, int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate,
			Date taskActualEndDate, int companyId, int taskParent, boolean havingChild, int placementId,Date taskChangeDate, String reason,
			int employeeId, int internalId, String hiringCompanyName, String jobTitle, int jobLocationId,
			String jobAddress, String jobCity, String jobState, int experienceRequired, float rate, Date datePosted,
			String jobLink, int jobPortalId, String jobReferenceNumber, int taxTypeId, String recruiterName,
			String recruiterEmail, String recruiterPhone, int jobSubmissionPortalId, String portalName,
			String commentOnCandidate, int minBillingRate, int externalId, String candidateName,
			String candidateCompany, String companyAddress, int visaId, int candidateExperience, int expectedMaxSalary,
			int expectedMinSalary, boolean willingToRelocate, boolean willingToNegotiateSalary,
			String reasonToFitForJob, int hiringCompanyId, int benchCandidateId) {
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
		this.havingChild = havingChild;
		this.placementId = placementId;
		this.taskChangeDate = taskChangeDate;
		this.reason = reason;
		this.employeeId = employeeId;
		this.internalId = internalId;
		this.hiringCompanyName = hiringCompanyName;
		this.jobTitle = jobTitle;
		this.jobLocationId = jobLocationId;
		this.jobAddress = jobAddress;
		this.jobCity = jobCity;
		this.jobState = jobState;
		this.experienceRequired = experienceRequired;
		this.rate = rate;
		this.datePosted = datePosted;
		this.jobLink = jobLink;
		this.jobPortalId = jobPortalId;
		this.jobReferenceNumber = jobReferenceNumber;
		this.taxTypeId = taxTypeId;
		this.recruiterName = recruiterName;
		this.recruiterEmail = recruiterEmail;
		this.recruiterPhone = recruiterPhone;
		this.jobSubmissionPortalId = jobSubmissionPortalId;
		this.portalName = portalName;
		this.commentOnCandidate = commentOnCandidate;
		this.minBillingRate = minBillingRate;
		this.externalId = externalId;
		this.candidateName = candidateName;
		this.candidateCompany = candidateCompany;
		this.companyAddress = companyAddress;
		this.visaId = visaId;
		this.candidateExperience = candidateExperience;
		this.expectedMaxSalary = expectedMaxSalary;
		this.expectedMinSalary = expectedMinSalary;
		this.willingToRelocate = willingToRelocate;
		this.willingToNegotiateSalary = willingToNegotiateSalary;
		this.reasonToFitForJob = reasonToFitForJob;
		this.hiringCompanyId = hiringCompanyId;
		this.benchCandidateId = benchCandidateId;
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
this.taskChangeDate = task.getTaskChangeDate();
		this.internalId = internalTask.getInternalId();
		this.benchCandidateId = internalTask.getBenchCandidateId();
		this.jobPortalId = internalTask.getJobPortalId();
		this.hiringCompanyName = internalTask.getHiringCompanyName();
		this.jobTitle = internalTask.getJobTitle();
		this.experienceRequired = internalTask.getExperienceRequired();
		this.jobLocationId = internalTask.getJobLocationId();
		this.jobReferenceNumber = internalTask.getJobReferenceNumber();
		this.rate = internalTask.getRate();
		this.recruiterName = internalTask.getVendorName();
		this.recruiterEmail = internalTask.getVendorEmail();
		this.recruiterPhone = internalTask.getVendorPhone();
		this.jobSubmissionPortalId = internalTask.getJobSubmissionPortalId();
		this.portalName = internalTask.getPortalName();
		this.datePosted = internalTask.getDatePosted();
		this.jobLink = internalTask.getJobLink();

		this.jobAddress = internalTask.getJobAddress();
		this.jobCity = internalTask.getJobCity();
		this.jobState = internalTask.getJobState();
		this.commentOnCandidate = internalTask.getCommentOnCandidate();

		this.minBillingRate = internalTask.getMinBillingRate();
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
this.taskChangeDate = task.getTaskChangeDate();
		this.externalId = externalTask.getExternalId();
		this.candidateName = externalTask.getCandidateName();
		this.candidateCompany = externalTask.getCandidateCompany();
		this.companyAddress = externalTask.getCompanyAddress();

		this.taxTypeId = externalTask.getTaxTypeId();

		this.recruiterName = externalTask.getRecruiterName();
		this.recruiterEmail = externalTask.getRecruiterEmail();
		this.recruiterPhone = externalTask.getRecruiterPhone();

		this.visaId = externalTask.getVisaId();

		this.candidateExperience = externalTask.getCandidateExperience();
		this.expectedMinSalary = externalTask.getExpectedMinSalary();
		this.expectedMaxSalary = externalTask.getExpectedMaxSalary();
		this.willingToRelocate = externalTask.isWillingToRelocate();
		this.willingToNegotiateSalary = externalTask.isWillingToNegotiateSalary();

		this.reasonToFitForJob = externalTask.getReasonToFitForJob();

		this.hiringCompanyId = externalTask.getHiringCompanyId();
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
	
	public Date getTaskChangeDate() {
		return taskChangeDate;
	}

	/**
	 * @param placementId the placementId to set
	 */
	public void setTaskChangeDate(Date taskChangeDate) {
		this.taskChangeDate = taskChangeDate;
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

	/**
	 * @return the reasonToFitForJob
	 */
	public String getReasonToFitForJob() {
		return reasonToFitForJob;
	}

	/**
	 * @param reasonToFitForJob the reasonToFitForJob to set
	 */
	public void setReasonToFitForJob(String reasonToFitForJob) {
		this.reasonToFitForJob = reasonToFitForJob;
	}

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
	 * @return the minBillingRate
	 */
	public int getMinBillingRate() {
		return minBillingRate;
	}

	/**
	 * @param minBillingRate the minBillingRate to set
	 */
	public void setMinBillingRate(int minBillingRate) {
		this.minBillingRate = minBillingRate;
	}

}
