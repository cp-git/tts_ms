package com.cpa.ttsms.dto;

import java.util.Date;

import com.cpa.ttsms.entity.ExternalTask;
import com.cpa.ttsms.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;

// not in use
public class ExternalTaskDTO {

	private int hiringCompanyId;

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

	private boolean havingChild;

	private int externalId; // Primary Key

	private String candidateCompany; // Name of the company for the candidate

	private String companyAddress; // Name of the company for the candidate

	private String recruiterName;

	private String recruiterEmail;

	private String recruiterPhone;

	private String candidateName; // Name of the candidate we are hiring or the person who is on the bench

	private int visaId; // Visa Type, Foreign key of table "visatype"

	private int taxTypeId;

	private int candidateExperience;

	private int expectedMaxSalary;

	private int expectedMinSalary;

	private boolean willingToRelocate;

	private boolean willingToNegotiateSalary;

	private String reasonToFitForJob;

	public ExternalTaskDTO(Task task, ExternalTask externalTask) {
		this.hiringCompanyId = externalTask.getHiringCompanyId();

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
		this.taxTypeId = externalTask.getTaxTypeId();
		this.visaId = externalTask.getVisaId();

		this.candidateExperience = externalTask.getCandidateExperience();
		this.expectedMinSalary = externalTask.getExpectedMinSalary();
		this.expectedMaxSalary = externalTask.getExpectedMaxSalary();
		this.willingToRelocate = externalTask.isWillingToRelocate();
		this.willingToNegotiateSalary = externalTask.isWillingToNegotiateSalary();
		this.reasonToFitForJob = externalTask.getReasonToFitForJob();

		this.candidateCompany = externalTask.getCandidateCompany();
		this.companyAddress = externalTask.getCompanyAddress();
		this.recruiterName = externalTask.getRecruiterName();
		this.recruiterEmail = externalTask.getRecruiterEmail();
		this.recruiterPhone = externalTask.getRecruiterPhone();

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

}
