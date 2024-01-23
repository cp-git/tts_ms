/**
 * @author - Code Generator
 * @createdOn 17/01/2024
 * @Description Controller class for hiringCompany
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.HiringCompanyController;
import com.cpa.ttsms.entity.HiringCompany;
import com.cpa.ttsms.repository.HiringCompanyRepo;
import com.cpa.ttsms.service.HiringCompanyService;

@Service
public class HiringCompanyServiceImpl implements HiringCompanyService {

	@Autowired
	private HiringCompanyRepo hiringCompanyRepo;
	private static Logger logger;

	public HiringCompanyServiceImpl() {
		logger = Logger.getLogger(HiringCompanyServiceImpl.class);
	}

	/**
	 * @param : HiringCompany hiringCompany
	 * @return : HiringCompany createdHiringCompany
	 * @description : For creating/inserting entry in hiringcompany table
	 */
	@Override
	public HiringCompany createHiringCompany(HiringCompany hiringCompany) {
		logger.debug("Entering createHiringCompany");
		HiringCompany createdHiringCompany = null;

		createdHiringCompany = hiringCompanyRepo.save(hiringCompany);
		logger.info("created HiringCompany :" + createdHiringCompany);
		return createdHiringCompany;
	}

	/**
	 * @param : String hiringCompanyId
	 * @return : HiringCompany hiringCompany
	 * @description : For get entry in hiringcompany table
	 */
	@Override
	public HiringCompany getHiringCompanyByHiringCompanyId(int hiringCompanyId) {
		logger.debug("Entering getHiringCompanyByHiringCompanyId");

		HiringCompany hiringCompany = hiringCompanyRepo.findByHiringCompanyId(hiringCompanyId);
		logger.info("Founded hiringCompany :" + hiringCompany);

		return hiringCompany;
	}

	/**
	 * @return : List<Object> hiringCompany
	 * @description : For fetching all hiringCompany which are active state from
	 *              hiringcompany table
	 */
	@Override
	public List<Object> getAllHiringCompanysByCompanyId(int companyId) {
		logger.debug("Entering getAllHiringCompanys");

		List<Object> hiringCompanys = hiringCompanyRepo.findAllByCompanyId(companyId);
		logger.info("Fetched all active hiringCompany :" + hiringCompanys);
		return hiringCompanys;
	}

	/**
	 * @param : HiringCompany to update
	 * @return : hiringCompany
	 * @description : For updating hiringCompany of hiringcompany table
	 */
	@Override
	public HiringCompany updateHiringCompanyByHiringCompanyId(HiringCompany hiringCompany, int hiringCompanyId) {
		logger.debug("Entering updateHiringCompany");

		HiringCompany toUpdatedHiringCompany = null;
		HiringCompany updatedHiringCompany = null;

		toUpdatedHiringCompany = hiringCompanyRepo.findByHiringCompanyId(hiringCompanyId);
		logger.info("exisitng HiringCompany :: " + toUpdatedHiringCompany);

		if (toUpdatedHiringCompany != null) {
			logger.debug("setting new data of HiringCompany to exisitng HiringCompany");

			if (hiringCompany.getHiringCompanyId() <= 0) {
				hiringCompany.setHiringCompanyId(hiringCompanyId);
			}

			updatedHiringCompany = hiringCompanyRepo.save(hiringCompany);

			logger.info("updated HiringCompany :" + updatedHiringCompany);
		}

		return updatedHiringCompany;
	}

	/**
	 * @param : String hiringCompanyId
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of
	 *              HiringCompany
	 * 
	 */
	@Override
	public int deleteHiringCompanyByHiringCompanyId(int hiringCompanyId) {
		logger.debug("Entering deleteHiringCompanyByHiringCompanyId");

		int count = hiringCompanyRepo.deleteByHiringCompanyId(hiringCompanyId);
		logger.info("deleted HiringCompany count : " + count);
		return count;
	}

}
