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

	@Column(name = "companyid")
	private int companyId;

	@Column(name = "isforbench")
	private boolean isForBench;

	@Column(name = "isforsourcing")
	private boolean isForSourcing;

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
	 * @param portalId
	 * @param portalName
	 * @param portalDescription
	 * @param companyId
	 * @param isForBench
	 * @param isForSourcing
	 */
	public JobPortal(int portalId, String portalName, String portalDescription, int companyId, boolean isForBench,
			boolean isForSourcing) {
		super();
		this.portalId = portalId;
		this.portalName = portalName;
		this.portalDescription = portalDescription;
		this.companyId = companyId;
		this.isForBench = isForBench;
		this.isForSourcing = isForSourcing;
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
				+ portalDescription + ", companyId=" + companyId + ", isForBench=" + isForBench + ", isForSourcing="
				+ isForSourcing + "]";
	}

}
