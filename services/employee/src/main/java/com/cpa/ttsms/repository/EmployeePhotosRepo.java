package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.EmployeePhotos;

public interface EmployeePhotosRepo extends JpaRepository<EmployeePhotos, Integer> {

	EmployeePhotos findByEmployeeId(int employeeId);
}
