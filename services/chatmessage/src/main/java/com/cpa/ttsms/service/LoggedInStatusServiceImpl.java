package com.cpa.ttsms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpa.ttsms.entity.LoggedInStatus;
import com.cpa.ttsms.repository.LoggedInStatusRepository;

@Service
public class LoggedInStatusServiceImpl implements LoggedInStatusService {

    @Autowired
    private LoggedInStatusRepository loggedInStatusRepository;

    @Override
    public List<LoggedInStatus> findAll() {
        return loggedInStatusRepository.findAll();
    }

    @Override
    public LoggedInStatus findById(int id) {
        return loggedInStatusRepository.findById(id).orElse(null);
    }

    @Override
    public LoggedInStatus save(LoggedInStatus status) {
        return loggedInStatusRepository.save(status);
    }

    @Override
    public void deleteById(int id) {
        loggedInStatusRepository.deleteById(id);
    }

	@Override
	public LoggedInStatus findByUsername(String username) {
		
		        return loggedInStatusRepository.findByUsername(username);
		
	
	}
}