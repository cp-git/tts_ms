/**
 * @author  - Code Generator
 * @createdOn -  12/12/2023
 * @Description Entity class for visa
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
@Table(name = "visatype")
public class Visa {

//TODO - add attributed and genrate setters and getters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visaid")
	private int visaId;

	@Column(name = "visatype")
	private String visaType;

	@Column(name = "visadescription")
	private String visaDescription;

	@Column(name = "companyid")
	private int companyId;

	@Column(name = "isforbench")
	private boolean isForBench;

	@Column(name = "isforsourcing")
	private boolean isForSourcing;

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
	 * @return the visaType
	 */
	public String getVisaType() {
		return visaType;
	}

	/**
	 * @param visaType the visaType to set
	 */
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	/**
	 * @return the visaDescription
	 */
	public String getVisaDescription() {
		return visaDescription;
	}

	/**
	 * @param visaDescription the visaDescription to set
	 */
	public void setVisaDescription(String visaDescription) {
		this.visaDescription = visaDescription;
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
	 * @return the isForBench
	 */
	public boolean isForBench() {
		return isForBench;
	}

	/**
	 * @param isForBench the isForBench to set
	 */
	public void setForBench(boolean isForBench) {
		this.isForBench = isForBench;
	}

	/**
	 * @return the isForSourcing
	 */
	public boolean isForSourcing() {
		return isForSourcing;
	}

	/**
	 * @param isForSourcing the isForSourcing to set
	 */
	public void setForSourcing(boolean isForSourcing) {
		this.isForSourcing = isForSourcing;
	}

	/**
	 * @param visaId
	 * @param visaType
	 * @param visaDescription
	 * @param companyId
	 * @param isForBench
	 * @param isForSourcing
	 */
	public Visa(int visaId, String visaType, String visaDescription, int companyId, boolean isForBench,
			boolean isForSourcing) {
		super();
		this.visaId = visaId;
		this.visaType = visaType;
		this.visaDescription = visaDescription;
		this.companyId = companyId;
		this.isForBench = isForBench;
		this.isForSourcing = isForSourcing;
	}

	/**
	 * 
	 */
	public Visa() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Visa [visaId=" + visaId + ", visaType=" + visaType + ", visaDescription=" + visaDescription
				+ ", companyId=" + companyId + ", isForBench=" + isForBench + ", isForSourcing=" + isForSourcing + "]";
	}

}
