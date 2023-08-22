package com.cpa.ttsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeephotos")
public class EmployeePhotos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int employeePhotosId;
	@Column(name = "employeeid")
	private int employeeId;
	@Column(name = "filename")
	private String fileName;

	/**
	 * @return the employeePhotosId
	 */
	public int getEmployeePhotosId() {
		return employeePhotosId;
	}

	/**
	 * @param employeePhotosId the employeePhotosId to set
	 */
	public void setEmployeePhotosId(int employeePhotosId) {
		this.employeePhotosId = employeePhotosId;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	 * @param employeePhotosId
	 * @param employeeId
	 * @param fileName
	 */
	public EmployeePhotos(int employeePhotosId, int employeeId, String fileName) {
		super();
		this.employeePhotosId = employeePhotosId;
		this.employeeId = employeeId;
		this.fileName = fileName;
	}

	/**
	 * 
	 */
	public EmployeePhotos() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeePhotos [employeePhotosId=" + employeePhotosId + ", employeeId=" + employeeId + ", fileName="
				+ fileName + "]";
	}

}