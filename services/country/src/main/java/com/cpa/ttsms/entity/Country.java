/**
 * @author  - Code Generator
 * @createdOn -  24/07/2023
 * @Description Entity class for Country
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
@Table(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int countryId;

	@Column(name = "code")
	private int countryCode;

	@Column(name = "name")
	private String countryName;

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryCode
	 */
	public int getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @param countryId
	 * @param countryCode
	 * @param countryName
	 */
	public Country(int countryId, int countryCode, String countryName) {
		super();
		this.countryId = countryId;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	/**
	 * 
	 */
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryCode=" + countryCode + ", countryName=" + countryName
				+ "]";
	}

//TODO - add attributed and genrate setters and getters

}
