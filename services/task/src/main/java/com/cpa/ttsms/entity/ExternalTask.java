package com.cpa.ttsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cpa.ttsms.dto.InternalExternalTaskDTO;

@Entity
@Table(name = "externaltask")
public class ExternalTask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "externalid")
	private int externalId;

	@Column(name = "candidatename")
	private String candidateName;

	@Column(name = "visaid")
	private int visaId; // Visa Type, Foreign key of table "visatype"

	@Column(name = "taxtypeid")
	private int taxTypeId; // Foreign key of table "jobtype"

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

	@Column(name = "reasontofitforjob")
	private String reasonToFitForJob;

	@Column(name = "candidatecompany")
	private String candidateCompany; // Name of the company for the candidate

	@Column(name = "companyaddress")
	private String companyAddress; // Name of the company for the candidate

	@Column(name = "recruitername")
	private String recruiterName;

	@Column(name = "recruiteremail")
	private String recruiterEmail;

	@Column(name = "recruiterphone")
	private String recruiterPhone;

	@Column(name = "taskid")
	private int taskId;

	@Column(name = "hiringcompanyid")
	private int hiringCompanyId;

	/**
	 * 
	 */
	public ExternalTask() {
		super();
	}

	// extracting data from internalExternalTaskDTO to externalTask
	public static ExternalTask setExternalTaskData(ExternalTask externalTask,
			InternalExternalTaskDTO internalExternalTaskDTO) {
		externalTask.setCandidateName(internalExternalTaskDTO.getCandidateName());
		externalTask.setCandidateCompany(internalExternalTaskDTO.getCandidateCompany());
		externalTask.setCompanyAddress(internalExternalTaskDTO.getCompanyAddress());
		externalTask.setTaxTypeId(internalExternalTaskDTO.getTaxTypeId());
		externalTask.setRecruiterName(internalExternalTaskDTO.getRecruiterName());
		externalTask.setRecruiterEmail(internalExternalTaskDTO.getRecruiterEmail());
		externalTask.setRecruiterPhone(internalExternalTaskDTO.getRecruiterPhone());
		externalTask.setVisaId(internalExternalTaskDTO.getVisaId());

		externalTask.setCandidateExperience(internalExternalTaskDTO.getCandidateExperience());
		externalTask.setExpectedMinSalary(internalExternalTaskDTO.getExpectedMinSalary());
		externalTask.setExpectedMaxSalary(internalExternalTaskDTO.getExpectedMaxSalary());
		externalTask.setWillingToRelocate(internalExternalTaskDTO.isWillingToRelocate());
		externalTask.setWillingToNegotiateSalary(internalExternalTaskDTO.isWillingToNegotiateSalary());

		externalTask.setReasonToFitForJob(internalExternalTaskDTO.getReasonToFitForJob());
		externalTask.setHiringCompanyId(internalExternalTaskDTO.getHiringCompanyId());

		return externalTask;
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

	@Override
	public String toString() {
		return "ExternalTask [externalId=" + externalId + ", candidateName=" + candidateName + ", visaId=" + visaId
				+ ", taxTypeId=" + taxTypeId + ", candidateExperience=" + candidateExperience + ", expectedMaxSalary="
				+ expectedMaxSalary + ", expectedMinSalary=" + expectedMinSalary + ", willingToRelocate="
				+ willingToRelocate + ", willingToNegotiateSalary=" + willingToNegotiateSalary + ", reasonToFitForJob="
				+ reasonToFitForJob + ", candidateCompany=" + candidateCompany + ", companyAddress=" + companyAddress
				+ ", recruiterName=" + recruiterName + ", recruiterEmail=" + recruiterEmail + ", recruiterPhone="
				+ recruiterPhone + ", taskId=" + taskId + ", hiringCompanyId=" + hiringCompanyId + "]";
	}

}
