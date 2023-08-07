/**
 * This class provides the implementation for the ReasonService interface, handling
 * CRUD operations related to reasons in the application.
 * 
 * @author Akash
 * @createdOn 07-08-2023
 * @Description Controller class for handling reason-related operations.
 */
package com.cpa.ttsms.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.ReasonController;
import com.cpa.ttsms.entity.Reason;
import com.cpa.ttsms.repository.ReasonRepo;
import com.cpa.ttsms.service.ReasonService;

@Service
public class ReasonServiceImpl implements ReasonService {

	// Autowired annotation is used to inject the ReasonRepo instance.
	@Autowired
	private ReasonRepo reasonRepo;

	// Static logger instance for logging events within this service.
	private static Logger logger;

	/**
	 * Constructor to initialize the logger for this service implementation.
	 */
	public ReasonServiceImpl() {
		logger = Logger.getLogger(ReasonServiceImpl.class);
	}

	/**
	 * Creates a new reason and stores it in the system.
	 * 
	 * @param reason The Reason object to be created.
	 * @return The created Reason object.
	 */
	@Override
	public Reason createReason(Reason reason) {
		logger.debug("Entering createReason");
		Reason createdReason = null;
		createdReason = reasonRepo.save(reason);
		logger.info("Created Reason: " + createdReason);
		return createdReason;
	}

	/**
	 * Retrieves a list of all reasons stored in the system.
	 * 
	 * @return A list of all Reason objects.
	 */
	@Override
	public List<Reason> getAllReasons() {
		logger.debug("Entering getAllReasons");

		List<Reason> reasons = reasonRepo.findAll();
		logger.info("Fetched all reasons: " + reasons.size());
		return reasons;
	}

	/**
	 * Retrieves a list of reasons associated with a specific task.
	 * 
	 * @param taskId The ID of the task for which reasons are to be retrieved.
	 * @return A list of Reason objects associated with the specified task.
	 */
	@Override
	public List<Reason> getReasonsByTaskId(int taskId) {
		logger.debug("Entering getReasonsByTaskId");
		List<Reason> reasons = reasonRepo.findByTaskId(taskId);
		logger.info("Found reasons for Task ID " + taskId + ": " + reasons.size());
		return reasons;
	}
}
