package com.cpa.ttsms.authlogin.service;

import com.cpa.ttsms.authlogin.entity.Employee;

public interface EmployeeService {

	public Employee getEmployeeByUsername(String username);
}
