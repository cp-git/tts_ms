package com.cpa.ttsms.dto;

import java.util.Date;

import com.cpa.ttsms.entity.InternalTask;
import com.cpa.ttsms.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;

// not in use
public class InternalTaskDTO {

	private int benchCandidateId;

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

	private int placementId;

	private int taskParent;

	private String reason;

	private int employeeId;

	private String fileName;

	private Date taskChangeDate;

	private boolean havingChild;

	private int internalId;

	private String hiringCompanyName; // Name of the company who advertised for a job

	private String jobTitle; // Job Title

	private int jobLocationId; // Foreign key of table "joblocation" (Job Mode)

	private String jobAddress;

	private String jobCity;

	private String jobState;

	private int experienceRequired; // Indicates the number of years of experience needed for the job

	private float rate; // $ per hour

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datePosted;

	private String jobLink;

	private int jobPortalId; // Foreign key of table "jobportal"

	private String jobReferenceNumber;

	private int taxTypeId; // Foreign key of table "jobtype"

	private String vendorName;

	private String vendorEmail;

	private String vendorPhone;

	private int jobSubmissionPortalId; // Foreign key of portal id

	private String portalName;

	private String commentsOnCandidate;

	public int getBenchCandidateId() {
		return benchCandidateId;
	}

	public void setBenchCandidateId(int benchCandidateId) {
		this.benchCandidateId = benchCandidateId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public int getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(int taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	public int getTaskAssignedTo() {
		return taskAssignedTo;
	}

	public void setTaskAssignedTo(int taskAssignedTo) {
		this.taskAssignedTo = taskAssignedTo;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public Date getTaskActualStartDate() {
		return taskActualStartDate;
	}

	public void setTaskActualStartDate(Date taskActualStartDate) {
		this.taskActualStartDate = taskActualStartDate;
	}

	public Date getTaskActualEndDate() {
		return taskActualEndDate;
	}

	public void setTaskActualEndDate(Date taskActualEndDate) {
		this.taskActualEndDate = taskActualEndDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getPlacementId() {
		return placementId;
	}

	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}

	public int getTaskParent() {
		return taskParent;
	}

	public void setTaskParent(int taskParent) {
		this.taskParent = taskParent;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getTaskChangeDate() {
		return taskChangeDate;
	}

	public void setTaskChangeDate(Date taskChangeDate) {
		this.taskChangeDate = taskChangeDate;
	}

	public boolean isHavingChild() {
		return havingChild;
	}

	public void setHavingChild(boolean havingChild) {
		this.havingChild = havingChild;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public String getHiringCompanyName() {
		return hiringCompanyName;
	}

	public void setHiringCompanyName(String hiringCompanyName) {
		this.hiringCompanyName = hiringCompanyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getJobLocationId() {
		return jobLocationId;
	}

	public void setJobLocationId(int jobLocationId) {
		this.jobLocationId = jobLocationId;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public String getJobCity() {
		return jobCity;
	}

	public void setJobCity(String jobCity) {
		this.jobCity = jobCity;
	}

	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public int getExperienceRequired() {
		return experienceRequired;
	}

	public void setExperienceRequired(int experienceRequired) {
		this.experienceRequired = experienceRequired;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public String getJobLink() {
		return jobLink;
	}

	public void setJobLink(String jobLink) {
		this.jobLink = jobLink;
	}

	public int getJobPortalId() {
		return jobPortalId;
	}

	public void setJobPortalId(int jobPortalId) {
		this.jobPortalId = jobPortalId;
	}

	public String getJobReferenceNumber() {
		return jobReferenceNumber;
	}

	public void setJobReferenceNumber(String jobReferenceNumber) {
		this.jobReferenceNumber = jobReferenceNumber;
	}

	public int getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getVendorPhone() {
		return vendorPhone;
	}

	public void setVendorPhone(String vendorPhone) {
		this.vendorPhone = vendorPhone;
	}

	public int getJobSubmissionPortalId() {
		return jobSubmissionPortalId;
	}

	public void setJobSubmissionPortalId(int jobSubmissionPortalId) {
		this.jobSubmissionPortalId = jobSubmissionPortalId;
	}

	public String getPortalName() {
		return portalName;
	}

	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	public String getCommentsOnCandidate() {
		return commentsOnCandidate;
	}

	public void setCommentsOnCandidate(String commentsOnCandidate) {
		this.commentsOnCandidate = commentsOnCandidate;
	}

	public InternalTaskDTO(int benchCandidateId, int taskId, String taskName, String taskDescription, int taskCreatedBy,
			int taskAssignedTo, int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate,
			Date taskActualEndDate, int companyId, int placementId, int taskParent, String reason, int employeeId,
			String fileName, Date taskChangeDate, boolean havingChild, int internalId, String hiringCompanyName,
			String jobTitle, int jobLocationId, String jobAddress, String jobCity, String jobState,
			int experienceRequired, float rate, Date datePosted, String jobLink, int jobPortalId,
			String jobReferenceNumber, int taxTypeId, String vendorName, String vendorEmail, String vendorPhone,
			int jobSubmissionPortalId, String portalName, String commentsOnCandidate) {
		super();
		this.benchCandidateId = benchCandidateId;
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
		this.placementId = placementId;
		this.taskParent = taskParent;
		this.reason = reason;
		this.employeeId = employeeId;
		this.fileName = fileName;
		this.taskChangeDate = taskChangeDate;
		this.havingChild = havingChild;
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
		this.vendorName = vendorName;
		this.vendorEmail = vendorEmail;
		this.vendorPhone = vendorPhone;
		this.jobSubmissionPortalId = jobSubmissionPortalId;
		this.portalName = portalName;
		this.commentsOnCandidate = commentsOnCandidate;
	}

//	public InternalTaskDTO(Task associatedTask, InternalTask internalTask) {
//	
//		// TODO Auto-generated constructor stub
//	}
	public InternalTaskDTO(Task task, InternalTask internalTask) {
		this.benchCandidateId = internalTask.getBenchCandidateId();

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

		this.hiringCompanyName = internalTask.getHiringCompanyName();
		this.jobTitle = internalTask.getJobTitle();
		this.jobLocationId = internalTask.getJobLocationId();
		this.jobAddress = internalTask.getJobAddress();
		this.jobCity = internalTask.getJobCity();
		this.jobState = internalTask.getJobState();
		this.experienceRequired = internalTask.getExperienceRequired();
		this.rate = internalTask.getRate();
		this.datePosted = internalTask.getDatePosted();
		this.jobLink = internalTask.getJobLink();
		this.jobPortalId = internalTask.getJobPortalId();
		this.jobReferenceNumber = internalTask.getJobReferenceNumber();

		this.vendorName = internalTask.getVendorName();
		this.vendorEmail = internalTask.getVendorEmail();
		this.vendorPhone = internalTask.getVendorPhone();

		this.jobSubmissionPortalId = internalTask.getJobSubmissionPortalId();
		this.portalName = internalTask.getPortalName();
		this.commentsOnCandidate = internalTask.getCommentOnCandidate();
	}

	@Override
	public String toString() {
		return "InternalTaskDTO [benchCandidateId=" + benchCandidateId + ", taskId=" + taskId + ", taskName=" + taskName
				+ ", taskDescription=" + taskDescription + ", taskCreatedBy=" + taskCreatedBy + ", taskAssignedTo="
				+ taskAssignedTo + ", taskStatus=" + taskStatus + ", taskStartDate=" + taskStartDate + ", taskEndDate="
				+ taskEndDate + ", taskActualStartDate=" + taskActualStartDate + ", taskActualEndDate="
				+ taskActualEndDate + ", companyId=" + companyId + ", placementId=" + placementId + ", taskParent="
				+ taskParent + ", reason=" + reason + ", employeeId=" + employeeId + ", fileName=" + fileName
				+ ", taskChangeDate=" + taskChangeDate + ", havingChild=" + havingChild + ", internalId=" + internalId
				+ ", hiringCompanyName=" + hiringCompanyName + ", jobTitle=" + jobTitle + ", jobLocationId="
				+ jobLocationId + ", jobAddress=" + jobAddress + ", jobCity=" + jobCity + ", jobState=" + jobState
				+ ", experienceRequired=" + experienceRequired + ", rate=" + rate + ", datePosted=" + datePosted
				+ ", jobLink=" + jobLink + ", jobPortalId=" + jobPortalId + ", jobReferenceNumber=" + jobReferenceNumber
				+ ", taxTypeId=" + taxTypeId + ", vendorName=" + vendorName + ", vendorEmail=" + vendorEmail
				+ ", vendorPhone=" + vendorPhone + ", jobSubmissionPortalId=" + jobSubmissionPortalId + ", portalName="
				+ portalName + ", commentsOnCandidate=" + commentsOnCandidate + "]";
	}

}
