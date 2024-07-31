/**
 * @author - Code Generator
 * @createdOn 19/12/2023
 * @Description Controller class for leadgeneration
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.LeadGenerationController;
import com.cpa.ttsms.entity.LeadGeneration;
import com.cpa.ttsms.repository.LeadGenerationRepo;
import com.cpa.ttsms.service.LeadGenerationService;

@Service
public class LeadGenerationServiceImpl implements LeadGenerationService {

	@Autowired
	private LeadGenerationRepo leadgenerationRepo;
	private static Logger logger;

	public LeadGenerationServiceImpl() {
		logger = Logger.getLogger(LeadGenerationServiceImpl.class);
	}

	/**
	 * @param : LeadGeneration leadgeneration
	 * @return : LeadGeneration createdLeadGeneration
	 * @description : For creating/inserting entry in leadrepo table
	 */
	@Override
	public LeadGeneration createLeadGeneration(LeadGeneration leadgeneration) {
		logger.debug("Entering createLeadGeneration");
		LeadGeneration createdLeadGeneration = null;

	//	leadgeneration.setLeadGenerationCreatedBy("admin");
	//	leadgeneration.setLeadGenerationModifiedBy("admin");

		createdLeadGeneration = leadgenerationRepo.save(leadgeneration);
		logger.info("created LeadGeneration :" + createdLeadGeneration);
		return createdLeadGeneration;
	}

	/**
	 * @param : String locationid
	 * @return : LeadGeneration leadgeneration
	 * @description : For get entry in leadrepo table
	 */
	@Override
	public LeadGeneration getLeadGenerationBylocationId(String locationid) {
		logger.debug("Entering getLeadGenerationBylocationId");

		LeadGeneration leadgeneration = leadgenerationRepo.findByleadgenerationlocationId(locationid);
		logger.info("Founded leadgeneration :" + leadgeneration);

		return leadgeneration;
	}

	/**
	 * @return : List<Object> leadgeneration
	 * @description : For fetching all leadgeneration which are active state from leadrepo table
	 */
	@Override
	public List<Object> getAllLeadGenerations() {
		logger.debug("Entering getAllLeadGenerations");

		List<Object> leadgenerations = leadgenerationRepo.findByleadgenerationIsActiveTrue();
		logger.info("Fetched all active leadgeneration :" + leadgenerations);
		return leadgenerations;
	}

	/**
	 * @param : LeadGeneration to update
	 * @return : leadgeneration
	 * @description : For updating leadgeneration of leadrepo table
	 */
	@Override
	public LeadGeneration updateLeadGenerationBylocationId(LeadGeneration leadgeneration, String locationid) {
		logger.debug("Entering updateLeadGeneration");

		LeadGeneration toUpdatedLeadGeneration = null;
		LeadGeneration updatedLeadGeneration = null;

		toUpdatedLeadGeneration = leadgenerationRepo.findByleadgenerationlocationId(locationid);
		logger.info("exisitng LeadGeneration :: " + toUpdatedLeadGeneration);

		if (toUpdatedLeadGeneration != null) {
			logger.debug("setting new data of LeadGeneration to exisitng LeadGeneration");

//			leadgeneration.setModifiedBy("admin");
						
			updatedLeadGeneration = leadgenerationRepo.save(leadgeneration);

			logger.info("updated LeadGeneration :" + updatedLeadGeneration);
		}

		return updatedLeadGeneration;
	}

	/**
	 * @param : String locationid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of LeadGeneration
	 * 
	 */
	@Override
	public int deleteLeadGenerationBylocationId(String locationid) {
		logger.debug("Entering deleteLeadGenerationBylocationId");

		int count =  leadgenerationRepo.deleteleadgenerationBylocationId(locationid);
		logger.info("deleted LeadGeneration count : " + count);
		return count;
	}

}
