/**
 * @author  - Code Generator
 * @createdOn -  24/07/2023
 * @Description Entity class for Company
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
@Table(name = "company")
public class Company {

//TODO - add attributed and genrate setters and getters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int companyId;

	@Column(name = "code")
	private String companyCode;

	@Column(name = "name")
	private String companyName;

	@Column(name = "contactemail")
	private String companyContactEmail;

	@Column(name = "contactphone")
	private String companyContactPhone;

	@Column(name = "address")
	private String companyAddress;

	@Column(name = "zip")
	private String companyZip;

	@Column(name = "countryid")
	private int companyCountryId;

	/**
	 * 
	 */
	public Company() {
		super();
	}

	/**
	 * @param companyId
	 * @param companyCode
	 * @param companyName
	 * @param companyContactEmail
	 * @param companyContactPhone
	 * @param companyAddress
	 * @param companyZip
	 * @param companyCountryId
	 */
	public Company(int companyId, String companyCode, String companyName, String companyContactEmail,
			String companyContactPhone, String companyAddress, String companyZip, int companyCountryId) {
		super();
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyContactEmail = companyContactEmail;
		this.companyContactPhone = companyContactPhone;
		this.companyAddress = companyAddress;
		this.companyZip = companyZip;
		this.companyCountryId = companyCountryId;
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
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyContactEmail
	 */
	public String getCompanyContactEmail() {
		return companyContactEmail;
	}

	/**
	 * @param companyContactEmail the companyContactEmail to set
	 */
	public void setCompanyContactEmail(String companyContactEmail) {
		this.companyContactEmail = companyContactEmail;
	}

	/**
	 * @return the companyContactPhone
	 */
	public String getCompanyContactPhone() {
		return companyContactPhone;
	}

	/**
	 * @param companyContactPhone the companyContactPhone to set
	 */
	public void setCompanyContactPhone(String companyContactPhone) {
		this.companyContactPhone = companyContactPhone;
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
	 * @return the companyZip
	 */
	public String getCompanyZip() {
		return companyZip;
	}

	/**
	 * @param companyZip the companyZip to set
	 */
	public void setCompanyZip(String companyZip) {
		this.companyZip = companyZip;
	}

	/**
	 * @return the companyCountryId
	 */
	public int getCompanyCountryId() {
		return companyCountryId;
	}

	/**
	 * @param companyCountryId the companyCountryId to set
	 */
	public void setCompanyCountryId(int companyCountryId) {
		this.companyCountryId = companyCountryId;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ ", companyContactEmail=" + companyContactEmail + ", companyContactPhone=" + companyContactPhone
				+ ", companyAddress=" + companyAddress + ", companyZip=" + companyZip + ", companyCountryId="
				+ companyCountryId + "]";
	}

}
