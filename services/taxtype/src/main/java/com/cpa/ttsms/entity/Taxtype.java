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
	 * @param taxTypeId
	 * @param taxTypeName
	 * @param taxTypeDescription
	 */
	public Taxtype(int taxTypeId, String taxTypeName, String taxTypeDescription) {
		super();
		this.taxTypeId = taxTypeId;
		this.taxTypeName = taxTypeName;
		this.taxTypeDescription = taxTypeDescription;
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
				+ taxTypeDescription + "]";
	}

}
