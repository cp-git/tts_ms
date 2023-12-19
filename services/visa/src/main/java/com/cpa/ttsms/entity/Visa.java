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
	 * @return the visadescription
	 */
	public String getVisadescription() {
		return visaDescription;
	}

	/**
	 * @param visadescription the visadescription to set
	 */
	public void setVisadescription(String visadescription) {
		this.visaDescription = visadescription;
	}

	/**
	 * @param visaId
	 * @param visaType
	 * @param visadescription
	 */
	public Visa(int visaId, String visaType, String visadescription) {
		super();
		this.visaId = visaId;
		this.visaType = visaType;
		this.visaDescription = visadescription;
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
		return "Visa [visaId=" + visaId + ", visaType=" + visaType + ", visadescription=" + visaDescription + "]";
	}

}
