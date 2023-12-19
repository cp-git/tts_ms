/**
 * @author  - Code Generator
 * @createdOn -  19/12/2023
 * @Description Entity class for JobLocation Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.JobLocation;

public interface JobLocationService {

	JobLocation createJobLocation(JobLocation joblocation);

	JobLocation getJobLocationBylocationId(int locationid);

	List<Object> getAllJobLocations();

	JobLocation updateJobLocationBylocationId(JobLocation joblocation, int locationid);

	int deleteJobLocationBylocationId(int locationid);

}