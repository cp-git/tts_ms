/**
 * @author  - Code Generator
 * @createdOn -  17/01/2024
 * @Description Entity class for benchCandidate
 * 
 */

package com.cpa.ttsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "benchcandidate")
public class BenchCandidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidateid")
	private int benchCandidateId;

	@Column(name = "candidatename")
	private String benchCandidateName;

	@Column(name = "visatypeid")
	private int visaTypeId;

	@Column(name = "taxtypeid")
	private int taxTypeId;

	@Column(name = "experience")
	private int experience;

	@Column(name = "companyid")
	private int companyId;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "email")
	private String email;

	@Column(name = "contactno")
	private String contactNo;

	@Column(name = "currentdesignation")
	private String currentDesignation;

	@Column(name = "willingtorelocate")
	private boolean willingToRelocate;
	
	@Column(name = "userActive")
	private boolean userActive;

//	@Column(name = "minbillingrate")
//	private int minBillingRate;

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
	 * @return the benchCandidateName
	 */
	public String getBenchCandidateName() {
		return benchCandidateName;
	}

	/**
	 * @param benchCandidateName the benchCandidateName to set
	 */
	public void setBenchCandidateName(String benchCandidateName) {
		this.benchCandidateName = benchCandidateName;
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
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * @param experience the experience to set
	 */
	public void setExperience(int experience) {
		this.experience = experience;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the currentDesignation
	 */
	public String getCurrentDesignation() {
		return currentDesignation;
	}

	/**
	 * @param currentDesignation the currentDesignation to set
	 */
	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
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

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}
	
	
	

//	/**
//	 * @return the minBillingRate
//	 */
//	public int getMinBillingRate() {
//		return minBillingRate;
//	}
//
//	/**
//	 * @param minBillingRate the minBillingRate to set
//	 */
//	public void setMinBillingRate(int minBillingRate) {
//		this.minBillingRate = minBillingRate;
//	}

}
