package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Password;

@Repository
public interface PasswordRepo extends JpaRepository<Password, Integer> {
	
	// Retrieves an password by their employeeId.
	public Password findByEmployeeId(int employeeId);

}
