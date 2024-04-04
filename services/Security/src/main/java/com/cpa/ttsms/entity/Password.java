package com.cpa.ttsms.entity;

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

	@Column(name="isforgotpassword")
	private boolean isForgotPassword;

	public int getPasswordId() {
		return passwordId;
	}

	public void setPasswordId(int passwordId) {
		this.passwordId = passwordId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isForgotPassword() {
		return isForgotPassword;
	}

	public void setForgotPassword(boolean isForgotPassword) {
		this.isForgotPassword = isForgotPassword;
	}

	public Password(int passwordId, int employeeId, String username, String password, boolean isForgotPassword) {
		super();
		this.passwordId = passwordId;
		this.employeeId = employeeId;
		this.username = username;
		this.password = password;
		this.isForgotPassword = isForgotPassword;
	}

	public Password() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Password [passwordId=" + passwordId + ", employeeId=" + employeeId + ", username=" + username
				+ ", password=" + password + ", isForgotPassword=" + isForgotPassword + "]";
	}
}
