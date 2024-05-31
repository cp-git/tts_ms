package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.LoggedInStatus;

public interface LoggedInStatusService {
    List<LoggedInStatus> findAll();
    LoggedInStatus findById(int id);
    LoggedInStatus save(LoggedInStatus status);
    void deleteById(int id);
    LoggedInStatus findByUsername(String username) ;
}
