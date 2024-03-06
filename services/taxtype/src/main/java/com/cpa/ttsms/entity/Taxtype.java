/**
 * @author  - Code Generator
 * @createdOn -  18/12/2023
 * @Description Entity class for taxtype
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
@Table(name = "taxtype")
public class Taxtype {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taxtypeid")
	private int taxTypeId;

	@Column(name = "taxtypename")
	private String taxTypeName;

	@Column(name = "taxtypedescription")
	private String taxTypeDescription;

	@Column(name = "companyid")
	private int companyId;

	@Column(name = "isforbench")
	private boolean isForBench;

	@Column(name = "isforsourcing")
	private boolean isForSourcing;
	
	
	@Column(name = "taxActive")
	private boolean taxActive;
	
	
	

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
	 * @return the taxTypeName
	 */
	public String getTaxTypeName() {
		return taxTypeName;
	}

	/**
	 * @param taxTypeName the taxTypeName to set
	 */
	public void setTaxTypeName(String taxTypeName) {
		this.taxTypeName = taxTypeName;
	}

	/**
	 * @return the taxTypeDescription
	 */
	public String getTaxTypeDescription() {
		return taxTypeDescription;
	}

	/**
	 * @param taxTypeDescription the taxTypeDescription to set
	 */
	public void setTaxTypeDescription(String taxTypeDescription) {
		this.taxTypeDescription = taxTypeDescription;
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
	
	
	

	public boolean isTaxActive() {
		return taxActive;
	}

	public void setTaxActive(boolean taxActive) {
		this.taxActive = taxActive;
	}

	/**
	 * @param taxTypeId
	 * @param taxTypeName
	 * @param taxTypeDescription
	 * @param companyId
	 * @param isForBench
	 * @param isForSourcing
	 */
	
	public Taxtype(int taxTypeId, String taxTypeName, String taxTypeDescription, int companyId, boolean isForBench,
			boolean isForSourcing, boolean taxActive) {
		super();
		this.taxTypeId = taxTypeId;
		this.taxTypeName = taxTypeName;
		this.taxTypeDescription = taxTypeDescription;
		this.companyId = companyId;
		this.isForBench = isForBench;
		this.isForSourcing = isForSourcing;
		this.taxActive = taxActive;
	}
	
	
	

	/**
	 * 
	 */
	public Taxtype() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Taxtype [taxTypeId=" + taxTypeId + ", taxTypeName=" + taxTypeName + ", taxTypeDescription="
				+ taxTypeDescription + ", companyId=" + companyId + ", isForBench=" + isForBench + ", isForSourcing="
				+ isForSourcing + ", taxActive=" + taxActive + "]";
	}
	
	

	


}
