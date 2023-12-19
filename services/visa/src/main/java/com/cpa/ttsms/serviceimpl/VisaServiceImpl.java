/**
 * @author - Code Generator
 * @createdOn 12/12/2023
 * @Description Controller class for visa
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.VisaController;
import com.cpa.ttsms.entity.Visa;
import com.cpa.ttsms.repository.VisaRepo;
import com.cpa.ttsms.service.VisaService;

@Service
public class VisaServiceImpl implements VisaService {

	@Autowired
	private VisaRepo visaRepo;
	private static Logger logger;

	public VisaServiceImpl() {
		logger = Logger.getLogger(VisaServiceImpl.class);
	}

	/**
	 * @param : Visa visa
	 * @return : Visa createdVisa
	 * @description : For creating/inserting entry in visa table
	 */
	@Override
	public Visa createVisa(Visa visa) {
		logger.debug("Entering createVisa");
		Visa createdVisa = null;

		createdVisa = visaRepo.save(visa);
		logger.info("created Visa :" + createdVisa);
		return createdVisa;
	}

	/**
	 * @param : String visaid
	 * @return : Visa visa
	 * @description : For get entry in visa table
	 */
	@Override
	public Visa getVisaByvisaId(int visaid) {
		logger.debug("Entering getVisaByvisaId");

		Visa visa = visaRepo.findByVisaId(visaid);
		logger.info("Founded visa :" + visa);

		return visa;
	}

	/**
	 * @return : List<Object> visa
	 * @description : For fetching all visa which are active state from visa table
	 */
	@Override
	public List<Object> getAllVisas() {
		logger.debug("Entering getAllVisas");

		List<Visa> visas = visaRepo.findAll();
		List<Object> visasList = new ArrayList<>(visas);
		logger.info("Fetched all active visa :" + visas);
		return visasList;
	}

	/**
	 * @param : Visa to update
	 * @return : visa
	 * @description : For updating visa of visa table
	 */
	@Override
	public Visa updateVisaByvisaId(Visa visa, int visaid) {
		logger.debug("Entering updateVisa");

		Visa toUpdatedVisa = null;
		Visa updatedVisa = null;
		toUpdatedVisa = visaRepo.findByVisaId(visaid);

		logger.info("exisitng Visa :: " + toUpdatedVisa);

		if (toUpdatedVisa != null) {
			logger.debug("setting new data of Visa to exisitng Visa");
			toUpdatedVisa.setVisaType(visa.getVisaType());
			toUpdatedVisa.setVisadescription(visa.getVisadescription());
//			visa.setModifiedBy("admin");

			updatedVisa = visaRepo.save(visa);

			logger.info("updated Visa :" + updatedVisa);
		}

		return updatedVisa;
	}

	/**
	 * @param : String visaid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of Visa
	 * 
	 */
	@Override
	public int deleteVisaByvisaId(int visaid) {
		logger.debug("Entering deleteVisaByvisaId");

		int id = visaRepo.deleteVisaById(visaid);
		logger.info("deleted Visa count : ");
		return id;
	}

}
