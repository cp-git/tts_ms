package com.cpa.ttsms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.entity.LoggedInStatus;
import com.cpa.ttsms.repository.LoggedInStatusRepository;
import com.cpa.ttsms.service.LoggedInStatusService;

@RestController
@RequestMapping("/api/loggedinstatus")
@CrossOrigin
public class LoggedInStatusController {

    @Autowired
    private LoggedInStatusService loggedInStatusService;
    
    @Autowired
    private LoggedInStatusRepository inStatusRepository;

    @GetMapping
    public List<LoggedInStatus> getAllLoggedInStatuses() {
        return loggedInStatusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoggedInStatus> getLoggedInStatusById(@PathVariable int id) {
        LoggedInStatus status = loggedInStatusService.findById(id);
        if (status != null) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/updateByUsername/{username}")
    public ResponseEntity<LoggedInStatus> updateLoggedInStatus(@PathVariable String username, @RequestBody LoggedInStatus statusDetails) {
        LoggedInStatus status = loggedInStatusService.findByUsername(username);
        if (status != null) {
            status.setEmpId(statusDetails.getEmpId());
            status.setStatus(statusDetails.getStatus());
            status.setUsername(statusDetails.getUsername());
            status.setFirstName(statusDetails.getFirstName());
            status.setLastName(statusDetails.getLastName());
            LoggedInStatus updatedStatus = loggedInStatusService.save(status);
            return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/updateStatusByUsername/{username}/{status}")
    public ResponseEntity<LoggedInStatus> updateLoggedInStatus(
            @PathVariable String username,
            @PathVariable Integer status
          ) {
        
        LoggedInStatus currentStatus = loggedInStatusService.findByUsername(username);
        if (currentStatus != null) {
        
            if (status != null) {
                currentStatus.setStatus(status);
            }
        
          

            LoggedInStatus updatedStatus = loggedInStatusService.save(currentStatus);
            return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//
//    @GetMapping("/getByUsername/{username}")
//    public ResponseEntity<LoggedInStatus> getLoggedInStatusByUsername(@PathVariable String username) {
//        LoggedInStatus status = loggedInStatusService.findByUsername(username);
//        if (status != null) {
//            return new ResponseEntity<>(status, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    
    
    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<?> getLoggedInStatusByUsername(@PathVariable String username) {
        LoggedInStatus status = loggedInStatusService.findByUsername(username);
        if (status != null) {
            return ResponseEntity.ok(status); // Username found, return status
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public LoggedInStatus createLoggedInStatus(@RequestBody LoggedInStatus status) {
        return loggedInStatusService.save(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoggedInStatus> updateLoggedInStatus(@PathVariable int id, @RequestBody LoggedInStatus statusDetails) {
        LoggedInStatus status = loggedInStatusService.findById(id);
        if (status != null) {
            status.setEmpId(statusDetails.getEmpId());
            status.setStatus(statusDetails.getStatus());
            status.setUsername(statusDetails.getUsername());
            status.setFirstName(statusDetails.getFirstName());
            status.setLastName(statusDetails.getLastName());
            LoggedInStatus updatedStatus = loggedInStatusService.save(status);
            return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoggedInStatus(@PathVariable int id) {
        loggedInStatusService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}