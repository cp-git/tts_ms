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
	
	private boolean admin;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getPhotoFilename() {
		return photoFilename;
	}

	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public EmployeeAndEmployeePhotosDTO(int employeeId, int countryId, int companyId, String firstName, String lastName,
			Date birthDate, String employeeEmail, int photoId, String photoFilename, boolean admin) {
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
		this.admin = admin;
	}

	public EmployeeAndEmployeePhotosDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeAndEmployeePhotosDTO [employeeId=" + employeeId + ", countryId=" + countryId + ", companyId="
				+ companyId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", employeeEmail=" + employeeEmail + ", photoId=" + photoId + ", photoFilename=" + photoFilename
				+ ", admin=" + admin + "]";
	}

	
	

}
