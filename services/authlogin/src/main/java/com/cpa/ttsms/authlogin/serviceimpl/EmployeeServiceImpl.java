package com.cpa.ttsms.authlogin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpa.ttsms.authlogin.entity.Employee;
import com.cpa.ttsms.authlogin.entity.Password;
import com.cpa.ttsms.authlogin.repository.EmployeeRepo;
import com.cpa.ttsms.authlogin.repository.PasswordRepository;
import com.cpa.ttsms.authlogin.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PasswordRepository passwordRepo;

	@Override
	public Employee getEmployeeByUsername(String username) {

		Password password = passwordRepo.findByUsername(username);

		return employeeRepo.findByEmployeeId(password.getEmployeeId());
	}

}
