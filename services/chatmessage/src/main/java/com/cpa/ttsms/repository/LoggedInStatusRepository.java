package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.LoggedInStatus;

public interface LoggedInStatusRepository extends JpaRepository<LoggedInStatus, Integer> {
	
    LoggedInStatus findByUsername(String username);

}