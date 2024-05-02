package com.cpa.ttsms.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskDTO {

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

	private int benchCandidateId;

	private int hiringCompanyId;

	private String jobTitle; // Job Title

	private int experienceRequired; // Indicates the number of years of experience needed for the job

	private float rate; // $ per hour

	private int visaTypeId;

	private int jobLocationId; // job mode

	private int taxTypeId;

	private int candidateExperience;

	private int expectedMinSalary;

	private int expectedMaxSalary;

	public TaskDTO() {
		super();
	}

	public TaskDTO(int taskId, String taskName, String taskDescription, int taskCreatedBy, int taskAssignedTo,
			int taskStatus, Date taskStartDate, Date taskEndDate, Date taskActualStartDate, Date taskActualEndDate,
			int companyId, int taskParent, boolean havingChild, int placementId, Date taskChangeDate,
			int benchCandidateId, int hiringCompanyId, String jobTitle, int experienceRequired, float rate,
			int visaTypeId, int jobLocationId, int taxTypeId, int candidateExperience, int expectedMinSalary,
			int expectedMaxSalary) {
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
		this.benchCandidateId = benchCandidateId;
		this.hiringCompanyId = hiringCompanyId;
		this.jobTitle = jobTitle;
		this.experienceRequired = experienceRequired;
		this.rate = rate;
		this.visaTypeId = visaTypeId;
		this.jobLocationId = jobLocationId;
		this.taxTypeId = taxTypeId;
		this.candidateExperience = candidateExperience;
		this.expectedMinSalary = expectedMinSalary;
		this.expectedMaxSalary = expectedMaxSalary;
	}

	public TaskDTO(InternalExternalTaskDTO intExtParentTask) {
		this.taskId = intExtParentTask.getTaskId();
		this.taskName = intExtParentTask.getTaskName();
		this.taskDescription = intExtParentTask.getTaskDescription();
		this.taskCreatedBy = intExtParentTask.getTaskCreatedBy();
		this.taskAssignedTo = intExtParentTask.getTaskAssignedTo();
		this.taskStatus = intExtParentTask.getTaskStatus();
		this.taskStartDate = intExtParentTask.getTaskStartDate();
		this.taskEndDate = intExtParentTask.getTaskEndDate();
		this.taskActualStartDate = intExtParentTask.getTaskActualStartDate();
		this.taskActualEndDate = intExtParentTask.getTaskActualEndDate();
		this.companyId = intExtParentTask.getCompanyId();
		this.taskParent = intExtParentTask.getTaskParent();
		this.havingChild = intExtParentTask.isHavingChild();
		this.placementId = intExtParentTask.getPlacementId();
		this.taskChangeDate = intExtParentTask.getTaskChangeDate();
		this.benchCandidateId = intExtParentTask.getBenchCandidateId();
		this.hiringCompanyId = intExtParentTask.getHiringCompanyId();

		if (intExtParentTask.getJobTitle() != null) {
			this.jobTitle = intExtParentTask.getJobTitle();
		} else {
			this.jobTitle = intExtParentTask.getHiringCompanyJobTitle();
		}

		if (intExtParentTask.getExperienceRequired() != 0) {
			this.experienceRequired = intExtParentTask.getExperienceRequired();
		} else {
			this.experienceRequired = intExtParentTask.getHiringCompanyExperienceRequired();
		}

		if (intExtParentTask.getBenchCandidateVisaTypeId() != 0) {
			this.visaTypeId = intExtParentTask.getBenchCandidateVisaTypeId();
		} else {
			this.visaTypeId = intExtParentTask.getVisaId();
		}

		if (intExtParentTask.getRate() != 0) {
			this.rate = intExtParentTask.getRate();
		} else {
			this.rate = intExtParentTask.getHiringCompanyRate();
		}

		if (intExtParentTask.getJobLocationId() != 0) {
			this.jobLocationId = intExtParentTask.getJobLocationId();
		} else {
			this.jobLocationId = intExtParentTask.getHiringCompanyJobLocationId();
		}

		if (intExtParentTask.getTaxTypeId() != 0) {
			this.taxTypeId = intExtParentTask.getTaxTypeId();
		} else {
			this.taxTypeId = intExtParentTask.getHiringCompanyCandidateTaxTypeId();
		}

		if (intExtParentTask.getCandidateExperience() != 0) {
			this.candidateExperience = intExtParentTask.getCandidateExperience();
		} else {
			this.candidateExperience = intExtParentTask.getHiringCompanyCandidateExperience();
		}

		this.expectedMinSalary = intExtParentTask.getExpectedMinSalary();

		this.expectedMaxSalary = intExtParentTask.getExpectedMaxSalary();
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

	public int getTaskParent() {
		return taskParent;
	}

	public void setTaskParent(int taskParent) {
		this.taskParent = taskParent;
	}

	public boolean isHavingChild() {
		return havingChild;
	}

	public void setHavingChild(boolean havingChild) {
		this.havingChild = havingChild;
	}

	public int getPlacementId() {
		return placementId;
	}

	public void setPlacementId(int placementId) {
		this.placementId = placementId;
	}

	public Date getTaskChangeDate() {
		return taskChangeDate;
	}

	public void setTaskChangeDate(Date taskChangeDate) {
		this.taskChangeDate = taskChangeDate;
	}

	public int getBenchCandidateId() {
		return benchCandidateId;
	}

	public void setBenchCandidateId(int benchCandidateId) {
		this.benchCandidateId = benchCandidateId;
	}

	public int getHiringCompanyId() {
		return hiringCompanyId;
	}

	public void setHiringCompanyId(int hiringCompanyId) {
		this.hiringCompanyId = hiringCompanyId;
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
	 * @return the visaTypeId
	 */
	public int getVisaTypeId() {
		return visaTypeId;
	}

	/**
	 * @param visaTypeId the visaTypeId to set
	 */
	public void setVisaTypeId(int visaTypeId) {
		this.visaTypeId = visaTypeId;
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

	@Override
	public String toString() {
		return "TaskDTO [taskId=" + taskId + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskCreatedBy=" + taskCreatedBy + ", taskAssignedTo=" + taskAssignedTo + ", taskStatus="
				+ taskStatus + ", taskStartDate=" + taskStartDate + ", taskEndDate=" + taskEndDate
				+ ", taskActualStartDate=" + taskActualStartDate + ", taskActualEndDate=" + taskActualEndDate
				+ ", companyId=" + companyId + ", taskParent=" + taskParent + ", havingChild=" + havingChild
				+ ", placementId=" + placementId + ", taskChangeDate=" + taskChangeDate + ", benchCandidateId="
				+ benchCandidateId + ", hiringCompanyId=" + hiringCompanyId + ", jobTitle=" + jobTitle
				+ ", experienceRequired=" + experienceRequired + ", rate=" + rate + ", visaTypeId=" + visaTypeId
				+ ", jobLocationId=" + jobLocationId + ", taxTypeId=" + taxTypeId + ", candidateExperience="
				+ candidateExperience + ", expectedMinSalary=" + expectedMinSalary + ", expectedMaxSalary="
				+ expectedMaxSalary + "]";
	}

}
