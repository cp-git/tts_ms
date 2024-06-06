package com.cpa.ttsms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="loggedinstatus")
public class LoggedInStatus {

	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column
	private int empId;
	
	
	@Column
	private int status;
	
	@Column
	private String username;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public LoggedInStatus(int empId, int status, String username, String firstName, String lastName) {
		super();
		this.empId = empId;
		this.status = status;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public LoggedInStatus() {
		super();
	}
	
	
	
	
	
}
