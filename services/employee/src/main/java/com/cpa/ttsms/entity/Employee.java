/**
 * @author  - Code Generator
 * @createdOn -  25/07/2023
 * @Description Entity class for employee
 * 
 */

package com.cpa.ttsms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

//TODO - add attributed and genrate setters and getters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int employeeId;

	@Column(name = "countryid")
	private int countryId;

	@Column(name = "companyid")
	private int companyId;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "dob")
	private Date birthDate;

	@Column(name = "email")
	private String employeeEmail;

	@Column(name = "isadmin")
	private boolean admin;

	// boolean value for checking user has permission for view all task or not
	@Column(name = "showAllTasks")
	private boolean showAllTasks;

	@Column(name = "isonbench")
	private boolean isOnBench;

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
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the showAllTasks
	 */
	public boolean isShowAllTasks() {
		return showAllTasks;
	}

	/**
	 * @param showAllTasks the showAllTasks to set
	 */
	public void setShowAllTasks(boolean showAllTasks) {
		this.showAllTasks = showAllTasks;
	}

	/**
	 * @return the isOnBench
	 */
	public boolean isOnBench() {
		return isOnBench;
	}

	/**
	 * @param isOnBench the isOnBench to set
	 */
	public void setOnBench(boolean isOnBench) {
		this.isOnBench = isOnBench;
	}

	/**
	 * @param employeeId
	 * @param countryId
	 * @param companyId
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param employeeEmail
	 * @param admin
	 * @param showAllTasks
	 * @param isOnBench
	 */
	public Employee(int employeeId, int countryId, int companyId, String firstName, String lastName, Date birthDate,
			String employeeEmail, boolean admin, boolean showAllTasks, boolean isOnBench) {
		super();
		this.employeeId = employeeId;
		this.countryId = countryId;
		this.companyId = companyId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employeeEmail = employeeEmail;
		this.admin = admin;
		this.showAllTasks = showAllTasks;
		this.isOnBench = isOnBench;
	}

	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", countryId=" + countryId + ", companyId=" + companyId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", employeeEmail=" + employeeEmail + ", admin=" + admin + ", showAllTasks=" + showAllTasks
				+ ", isOnBench=" + isOnBench + "]";
	}

}
