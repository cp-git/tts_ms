/**
 * @author - Code Generator
 * @createdOn 18/12/2023
 * @Description Controller class for jobportal
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.JobPortalController;
import com.cpa.ttsms.entity.JobPortal;
import com.cpa.ttsms.repository.JobPortalRepo;
import com.cpa.ttsms.service.JobPortalService;

@Service
public class JobPortalServiceImpl implements JobPortalService {

	@Autowired
	private JobPortalRepo jobportalRepo;
	private static Logger logger;

	public JobPortalServiceImpl() {
		logger = Logger.getLogger(JobPortalServiceImpl.class);
	}

	/**
	 * @param : JobPortal jobportal
	 * @return : JobPortal createdJobPortal
	 * @description : For creating/inserting entry in jobportal table
	 */
	@Override
	public JobPortal createJobPortal(JobPortal jobportal) {
		logger.debug("Entering createJobPortal");
		JobPortal createdJobPortal = null;

		// jobportal.setJobPortalCreatedBy("admin");
		// jobportal.setJobPortalModifiedBy("admin");

		createdJobPortal = jobportalRepo.save(jobportal);
		logger.info("created JobPortal :" + createdJobPortal);
		return createdJobPortal;
	}

	/**
	 * @param : String jobportalid
	 * @return : JobPortal jobportal
	 * @description : For get entry in jobportal table
	 */
	@Override
	public JobPortal getJobPortalByjobPortalId(int portalId) {
		logger.debug("Entering getJobPortalByjobPortalId");

		JobPortal jobportal = jobportalRepo.findByPortalId(portalId);
		logger.info("Founded jobportal :" + jobportal);

		return jobportal;
	}

	/**
	 * @return : List<Object> jobportal
	 * @description : For fetching all jobportal which are active state from
	 *              jobportal table
	 */
	@Override
	public List<Object> getAllJobPortals() {
		logger.debug("Entering getAllJobPortals");

		List<JobPortal> jobportals = jobportalRepo.findAll();
		List<Object> portals = new ArrayList<>(jobportals);
		logger.info("Fetched all active jobportal :" + jobportals);
		return portals;
	}

	/**
	 * @param : JobPortal to update
	 * @return : jobportal
	 * @description : For updating jobportal of jobportal table
	 */
	@Override
	public JobPortal updateJobPortalByjobPortalId(JobPortal jobportal, int portalId) {
		logger.debug("Entering updateJobPortal");

		JobPortal toUpdatedJobPortal = null;
		JobPortal updatedJobPortal = null;

		toUpdatedJobPortal = jobportalRepo.findByPortalId(portalId);
		logger.info("exisitng JobPortal :: " + toUpdatedJobPortal);

		if (toUpdatedJobPortal != null) {
			logger.debug("setting new data of JobPortal to exisitng JobPortal");
			toUpdatedJobPortal.setPortalName(jobportal.getPortalName());
			toUpdatedJobPortal.setPortalDescription(jobportal.getPortalDescription());
			toUpdatedJobPortal.setCompanyId(jobportal.getCompanyId());
			toUpdatedJobPortal.setForBench(jobportal.isForBench());
			toUpdatedJobPortal.setForSourcing(jobportal.isForSourcing());

			updatedJobPortal = jobportalRepo.save(jobportal);

			logger.info("updated JobPortal :" + updatedJobPortal);
		}

		return updatedJobPortal;
	}

	/**
	 * @param : String jobportalid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of
	 *              JobPortal
	 * 
	 */
	@Override
	public int deleteJobPortalByjobPortalId(int portalId) {
		logger.debug("Entering deleteJobPortalByjobPortalId");

		int jobPortalId = jobportalRepo.deleteJobPortalById(portalId);
//		logger.info("deleted JobPortal count : " + count);
		return jobPortalId;
	}

	@Override
	public List<Object> getAllPortalsByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		List<JobPortal> portals = jobportalRepo.findByCompanyId(companyId);
		List<Object> obj = new ArrayList<>(portals);
		return obj;
	}

}
