package com.cpa.ttsms.dto;

import java.util.Date;

public class EmployeeAndEmployeePhotosDTO {
	private int employeeId;

	private int countryId;

	private int companyId;

	private String firstName;

	private String lastName;

	private Date birthDate;

	private String employeeEmail;

	private int photoId;

	private String photoFilename;

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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the employeeEmail
	 */
	public String getEmployeeEmail() {
		return employeeEmail;
	}

	/**
	 * @param employeeEmail the employeeEmail to set
	 */
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	/**
	 * @return the photoId
	 */
	public int getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return the photoFilename
	 */
	public String getPhotoFilename() {
		return photoFilename;
	}

	/**
	 * @param photoFilename the photoFilename to set
	 */
	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	/**
	 * @param employeeId
	 * @param countryId
	 * @param companyId
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param employeeEmail
	 * @param photoId
	 * @param photoFilename
	 */
	public EmployeeAndEmployeePhotosDTO(int employeeId, int countryId, int companyId, String firstName, String lastName,
			Date birthDate, String employeeEmail, int photoId, String photoFilename) {
		super();
		this.employeeId = employeeId;
		this.countryId = countryId;
		this.companyId = companyId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employeeEmail = employeeEmail;
		this.photoId = photoId;
		this.photoFilename = photoFilename;
	}

	/**
	 * 
	 */
	public EmployeeAndEmployeePhotosDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeAndEmployeePhotosDTO [employeeId=" + employeeId + ", countryId=" + countryId + ", companyId="
				+ companyId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", employeeEmail=" + employeeEmail + ", photoId=" + photoId + ", photoFilename=" + photoFilename
				+ "]";
	}

}
