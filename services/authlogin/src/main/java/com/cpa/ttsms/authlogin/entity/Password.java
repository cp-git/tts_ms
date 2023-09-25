package com.cpa.ttsms.authlogin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "password")
public class Password {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int passwordId;

	@Column(name = "empid")
	private int employeeId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	private String dataType;

	/**
	 * 
	 */
	public Password() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 */
	public Password(String username, String password, String dataType) {
		super();
		this.username = username;
		this.password = password;
		this.dataType = dataType;
	}

	/**
	 * @param passwordId
	 * @param employeeId
	 * @param username
	 * @param password
	 */
	public Password(int passwordId, int employeeId, String username, String password, String dataType) {
		super();
		this.passwordId = passwordId;
		this.employeeId = employeeId;
		this.username = username;
		this.password = password;
		this.dataType = dataType;
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
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "Password [passwordId=" + passwordId + ", employeeId=" + employeeId + ", username=" + username
				+ ", password=" + password + ", dataType=" + dataType + "]";
	}

}
