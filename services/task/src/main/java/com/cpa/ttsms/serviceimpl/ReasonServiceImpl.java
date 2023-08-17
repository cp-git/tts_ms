/**
 * This class provides the implementation for the ReasonService interface, handling
 * CRUD operations related to reasons in the application.
 * 
 * @author Akash
 * @createdOn 07-08-2023
 * @Description Controller class for handling reason-related operations.
 */
package com.cpa.ttsms.serviceimpl;

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
}
