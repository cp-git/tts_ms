/**
 * @author  - Code Generator
 * @createdOn -  18/12/2023
 * @Description Entity class for jobportal
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
@Table(name = "jobportal")
public class JobPortal {

//TODO - add attributed and genrate setters and getters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portalid")
	private int portalId;

	@Column(name = "portalname")
	private String portalName;

	@Column(name = "portaldescription")
	private String portalDescription;

	/**
	 * @return the portalId
	 */
	public int getPortalId() {
		return portalId;
	}

	/**
	 * @param portalId the portalId to set
	 */
	public void setPortalId(int portalId) {
		this.portalId = portalId;
	}

	/**
	 * @return the portalName
	 */
	public String getPortalName() {
		return portalName;
	}

	/**
	 * @param portalName the portalName to set
	 */
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	/**
	 * @return the portalDescription
	 */
	public String getPortalDescription() {
		return portalDescription;
	}

	/**
	 * @param portalDescription the portalDescription to set
	 */
	public void setPortalDescription(String portalDescription) {
		this.portalDescription = portalDescription;
	}

	/**
	 * @param portalId
	 * @param portalName
	 * @param portalDescription
	 */
	public JobPortal(int portalId, String portalName, String portalDescription) {
		super();
		this.portalId = portalId;
		this.portalName = portalName;
		this.portalDescription = portalDescription;
	}

	/**
	 * 
	 */
	public JobPortal() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JobPortal [portalId=" + portalId + ", portalName=" + portalName + ", portalDescription="
				+ portalDescription + "]";
	}

}
