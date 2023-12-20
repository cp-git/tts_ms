/**
 * @author  - Code Generator
 * @createdOn -  19/12/2023
 * @Description Entity class for joblocation
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
@Table(name = "joblocation")
public class JobLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locationid")
	private int locationId;

	@Column(name = "locationtype")
	private String locationType;

	@Column(name = "locationdescription")
	private String locationDescription;

	@Column(name = "companyid")
	private int companyId;

	@Column(name = "isforbench")
	private boolean isForBench;

	@Column(name = "isforsourcing")
	private boolean isForSourcing;

	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}

	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	/**
	 * @return the locationDescription
	 */
	public String getLocationDescription() {
		return locationDescription;
	}

	/**
	 * @param locationDescription the locationDescription to set
	 */
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
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
	 * @param locationId
	 * @param locationType
	 * @param locationDescription
	 * @param companyId
	 * @param isForBench
	 * @param isForSourcing
	 */
	public JobLocation(int locationId, String locationType, String locationDescription, int companyId,
			boolean isForBench, boolean isForSourcing) {
		super();
		this.locationId = locationId;
		this.locationType = locationType;
		this.locationDescription = locationDescription;
		this.companyId = companyId;
		this.isForBench = isForBench;
		this.isForSourcing = isForSourcing;
	}

	/**
	 * 
	 */
	public JobLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JobLocation [locationId=" + locationId + ", locationType=" + locationType + ", locationDescription="
				+ locationDescription + ", companyId=" + companyId + ", isForBench=" + isForBench + ", isForSourcing="
				+ isForSourcing + "]";
	}

//TODO - add attributed and genrate setters and getters

}
