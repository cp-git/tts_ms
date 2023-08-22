package com.cpa.ttsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companyphotos")
public class CompanyPhotos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int companyPhotosId;
	@Column(name = "companyid")
	private int companyId;
	@Column(name = "filename")
	private String fileName;

	/**
	 * @return the companyPhotosId
	 */
	public int getCompanyPhotosId() {
		return companyPhotosId;
	}

	/**
	 * @param companyPhotosId the companyPhotosId to set
	 */
	public void setCompanyPhotosId(int companyPhotosId) {
		this.companyPhotosId = companyPhotosId;
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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @param companyPhotosId
	 * @param companyId
	 * @param fileName
	 */
	public CompanyPhotos(int companyPhotosId, int companyId, String fileName) {
		super();
		this.companyPhotosId = companyPhotosId;
		this.companyId = companyId;
		this.fileName = fileName;
	}

	/**
	 *
	 */
	public CompanyPhotos() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CompanyPhotos [companyPhotosId=" + companyPhotosId + ", companyId=" + companyId + ", fileName="
				+ fileName + "]";
	}
}