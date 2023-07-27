package com.cpa.ttsms.entity;

public class EmployeeAndPassword {

	private int employeeId;

	private int countryId;

	private int companyId;

	private String firstName;

	private String lastName;

	private String birthDate;

	private String employeeEmail;

	private int passwordId;

	private String username;

	private String password;

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
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
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
	 * @return the passwordId
	 */
	public int getPasswordId() {
		return passwordId;
	}

	/**
	 * @param passwordId the passwordId to set
	 */
	public void setPasswordId(int passwordId) {
		this.passwordId = passwordId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param employeeId
	 * @param countryId
	 * @param companyId
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param employeeEmail
	 * @param passwordId
	 * @param username
	 * @param password
	 */
	public EmployeeAndPassword(int employeeId, int countryId, int companyId, String firstName, String lastName,
			String birthDate, String employeeEmail, int passwordId, String username, String password) {
		super();
		this.employeeId = employeeId;
		this.countryId = countryId;
		this.companyId = companyId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employeeEmail = employeeEmail;
		this.passwordId = passwordId;
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 */
	public EmployeeAndPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeAndPassword [employeeId=" + employeeId + ", countryId=" + countryId + ", companyId=" + companyId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", employeeEmail=" + employeeEmail + ", passwordId=" + passwordId + ", username=" + username
				+ ", password=" + password + "]";
	}

//	public void setBirthDate(String format) {
//		// TODO Auto-generated method stub
//
//	}

//	public void setBirthDate(String format) {
//		// TODO Auto-generated method stub
//
//	}

}
