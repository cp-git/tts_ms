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

	private Date taskChangeDate;

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

	public int getHiringCompanyId() {
		return hiringCompanyId;
	}

	public void setHiringCompanyId(int hiringCompanyId) {
		this.hiringCompanyId = hiringCompanyId;
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

	public int getExternalId() {
		return externalId;
	}

	public void setExternalId(int externalId) {
		this.externalId = externalId;
	}

	public String getCandidateCompany() {
		return candidateCompany;
	}

	public void setCandidateCompany(String candidateCompany) {
		this.candidateCompany = candidateCompany;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}

	public String getRecruiterPhone() {
		return recruiterPhone;
	}

	public void setRecruiterPhone(String recruiterPhone) {
		this.recruiterPhone = recruiterPhone;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getVisaId() {
		return visaId;
	}

	public void setVisaId(int visaId) {
		this.visaId = visaId;
	}

	public int getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public int getCandidateExperience() {
		return candidateExperience;
	}

	public void setCandidateExperience(int candidateExperience) {
		this.candidateExperience = candidateExperience;
	}

	public int getExpectedMaxSalary() {
		return expectedMaxSalary;
	}

	public void setExpectedMaxSalary(int expectedMaxSalary) {
		this.expectedMaxSalary = expectedMaxSalary;
	}

	public int getExpectedMinSalary() {
		return expectedMinSalary;
	}

	public void setExpectedMinSalary(int expectedMinSalary) {
		this.expectedMinSalary = expectedMinSalary;
	}

	public boolean isWillingToRelocate() {
		return willingToRelocate;
	}

	public void setWillingToRelocate(boolean willingToRelocate) {
		this.willingToRelocate = willingToRelocate;
	}

	public boolean isWillingToNegotiateSalary() {
		return willingToNegotiateSalary;
	}

	public void setWillingToNegotiateSalary(boolean willingToNegotiateSalary) {
		this.willingToNegotiateSalary = willingToNegotiateSalary;
	}

	public String getReasonToFitForJob() {
		return reasonToFitForJob;
	}

	public void setReasonToFitForJob(String reasonToFitForJob) {
		this.reasonToFitForJob = reasonToFitForJob;
	}

	public ExternalTaskDTO(int hiringCompanyId, int taskId, String taskName, String taskDescription, int taskCreatedBy,
			int taskAssignedTo, int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate,
			Date taskActualEndDate, int companyId, int placementId, int taskParent, String reason, int employeeId,
			String fileName, Date taskChangeDate, boolean havingChild, int externalId, String candidateCompany,
			String companyAddress, String recruiterName, String recruiterEmail, String recruiterPhone,
			String candidateName, int visaId, int taxTypeId, int candidateExperience, int expectedMaxSalary,
			int expectedMinSalary, boolean willingToRelocate, boolean willingToNegotiateSalary,
			String reasonToFitForJob) {
		super();
		this.hiringCompanyId = hiringCompanyId;
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
		this.externalId = externalId;
		this.candidateCompany = candidateCompany;
		this.companyAddress = companyAddress;
		this.recruiterName = recruiterName;
		this.recruiterEmail = recruiterEmail;
		this.recruiterPhone = recruiterPhone;
		this.candidateName = candidateName;
		this.visaId = visaId;
		this.taxTypeId = taxTypeId;
		this.candidateExperience = candidateExperience;
		this.expectedMaxSalary = expectedMaxSalary;
		this.expectedMinSalary = expectedMinSalary;
		this.willingToRelocate = willingToRelocate;
		this.willingToNegotiateSalary = willingToNegotiateSalary;
		this.reasonToFitForJob = reasonToFitForJob;
	}

//	public ExternalTaskDTO(Task associatedTask, ExternalTask externalTask) {
//		super();
//		// TODO Auto-generated constructor stub
//	}
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
		this.taskChangeDate = task.getTaskChangeDate();
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

	@Override
	public String toString() {
		return "ExternalTaskDTO [hiringCompanyId=" + hiringCompanyId + ", taskId=" + taskId + ", taskName=" + taskName
				+ ", taskDescription=" + taskDescription + ", taskCreatedBy=" + taskCreatedBy + ", taskAssignedTo="
				+ taskAssignedTo + ", taskStatus=" + taskStatus + ", taskStartDate=" + taskStartDate + ", taskEndDate="
				+ taskEndDate + ", taskActualStartDate=" + taskActualStartDate + ", taskActualEndDate="
				+ taskActualEndDate + ", companyId=" + companyId + ", placementId=" + placementId + ", taskParent="
				+ taskParent + ", reason=" + reason + ", employeeId=" + employeeId + ", fileName=" + fileName
				+ ", taskChangeDate=" + taskChangeDate + ", havingChild=" + havingChild + ", externalId=" + externalId
				+ ", candidateCompany=" + candidateCompany + ", companyAddress=" + companyAddress + ", recruiterName="
				+ recruiterName + ", recruiterEmail=" + recruiterEmail + ", recruiterPhone=" + recruiterPhone
				+ ", candidateName=" + candidateName + ", visaId=" + visaId + ", taxTypeId=" + taxTypeId
				+ ", candidateExperience=" + candidateExperience + ", expectedMaxSalary=" + expectedMaxSalary
				+ ", expectedMinSalary=" + expectedMinSalary + ", willingToRelocate=" + willingToRelocate
				+ ", willingToNegotiateSalary=" + willingToNegotiateSalary + ", reasonToFitForJob=" + reasonToFitForJob
				+ "]";
	}

}
