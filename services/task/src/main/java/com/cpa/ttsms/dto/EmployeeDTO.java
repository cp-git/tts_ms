package com.cpa.ttsms.dto;

import java.util.Date;

public class EmployeeDTO {
	private int employeeId;

	private int countryId;

	private int companyId;

	private String firstName;

	private String lastName;

	private Date birthDate;

	private String employeeEmail;

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

	public EmployeeDTO(int employeeId, int countryId, int companyId, String firstName, String lastName, Date birthDate,
			String employeeEmail) {
		super();
		this.employeeId = employeeId;
		this.countryId = countryId;
		this.companyId = companyId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employeeEmail = employeeEmail;
	}

	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", countryId=" + countryId + ", companyId=" + companyId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", employeeEmail=" + employeeEmail + "]";
	}
}
