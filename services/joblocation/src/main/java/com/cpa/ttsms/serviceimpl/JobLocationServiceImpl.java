/**
 * @author - Code Generator
 * @createdOn 19/12/2023
 * @Description Controller class for joblocation
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.JobLocationController;
import com.cpa.ttsms.entity.JobLocation;
import com.cpa.ttsms.repository.JobLocationRepo;
import com.cpa.ttsms.service.JobLocationService;

@Service
public class JobLocationServiceImpl implements JobLocationService {

	@Autowired
	private JobLocationRepo joblocationRepo;
	private static Logger logger;

	public JobLocationServiceImpl() {
		logger = Logger.getLogger(JobLocationServiceImpl.class);
	}

	/**
	 * @param : JobLocation joblocation
	 * @return : JobLocation createdJobLocation
	 * @description : For creating/inserting entry in joblocation table
	 */
	@Override
	public JobLocation createJobLocation(JobLocation joblocation) {
		logger.debug("Entering createJobLocation");
		JobLocation createdJobLocation = null;

		// joblocation.setJobLocationCreatedBy("admin");
		// joblocation.setJobLocationModifiedBy("admin");

		createdJobLocation = joblocationRepo.save(joblocation);
		logger.info("created JobLocation :" + createdJobLocation);
		return createdJobLocation;
	}

	/**
	 * @param : String locationid
	 * @return : JobLocation joblocation
	 * @description : For get entry in joblocation table
	 */
	@Override
	public JobLocation getJobLocationBylocationId(int locationid) {
		logger.debug("Entering getJobLocationBylocationId");

		JobLocation joblocation = joblocationRepo.findByLocationId(locationid);
		logger.info("Founded joblocation :" + joblocation);

		return joblocation;
	}

	/**
	 * @return : List<Object> joblocation
	 * @description : For fetching all joblocation which are active state from
	 *              joblocation table
	 */
	@Override
	public List<Object> getAllJobLocations() {
		logger.debug("Entering getAllJobLocations");

		List<JobLocation> joblocations = joblocationRepo.findAll();
		List<Object> locations = new ArrayList<>(joblocations);
		logger.info("Fetched all active joblocation :" + joblocations);
		return locations;
	}

	/**
	 * @param : JobLocation to update
	 * @return : joblocation
	 * @description : For updating joblocation of joblocation table
	 */
	@Override
	public JobLocation updateJobLocationBylocationId(JobLocation joblocation, int locationid) {
		logger.debug("Entering updateJobLocation");

		JobLocation toUpdatedJobLocation = null;
		JobLocation updatedJobLocation = null;

		toUpdatedJobLocation = joblocationRepo.findByLocationId(locationid);
		logger.info("exisitng JobLocation :: " + toUpdatedJobLocation);

		if (toUpdatedJobLocation != null) {
			logger.debug("setting new data of JobLocation to exisitng JobLocation");

//			joblocation.setModifiedBy("admin");

			toUpdatedJobLocation.setLocationType(joblocation.getLocationType());
			toUpdatedJobLocation.setLocationDescription(joblocation.getLocationDescription());
			toUpdatedJobLocation.setCompanyId(joblocation.getCompanyId());
			toUpdatedJobLocation.setForBench(joblocation.isForBench());
			toUpdatedJobLocation.setForSourcing(joblocation.isForSourcing());
			updatedJobLocation = joblocationRepo.save(joblocation);

			logger.info("updated JobLocation :" + updatedJobLocation);
		}

		return updatedJobLocation;
	}

	/**
	 * @param : String locationid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of
	 *              JobLocation
	 * 
	 */
	@Override
	public int deleteJobLocationBylocationId(int locationid) {
		logger.debug("Entering deleteJobLocationBylocationId");

		int jobLocationId = joblocationRepo.deleteJobLocationById(locationid);
//		int count =  joblocationRepo.deleteJobLocationBylocationId(locationid);
//		logger.info("deleted JobLocation count : " + count);
		return jobLocationId;
	}

	@Override
	public List<Object> getJobLocationByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		List<JobLocation> locations = joblocationRepo.findByCompanyId(companyId);
		List<Object> obj = new ArrayList<>(locations);
		return obj;
	}

}
