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
	 * @param locationId
	 * @param locationType
	 * @param locationDescription
	 */
	public JobLocation(int locationId, String locationType, String locationDescription) {
		super();
		this.locationId = locationId;
		this.locationType = locationType;
		this.locationDescription = locationDescription;
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
				+ locationDescription + "]";
	}

//TODO - add attributed and genrate setters and getters

}
