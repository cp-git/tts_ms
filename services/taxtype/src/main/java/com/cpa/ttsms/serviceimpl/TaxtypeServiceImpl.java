/**
 * @author - Code Generator
 * @createdOn 18/12/2023
 * @Description Controller class for taxtype
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.TaxtypeController;
import com.cpa.ttsms.entity.Taxtype;
import com.cpa.ttsms.repository.TaxtypeRepo;
import com.cpa.ttsms.service.TaxtypeService;

@Service
public class TaxtypeServiceImpl implements TaxtypeService {

	@Autowired
	private TaxtypeRepo taxtypeRepo;
	private static Logger logger;

	public TaxtypeServiceImpl() {
		logger = Logger.getLogger(TaxtypeServiceImpl.class);
	}

	/**
	 * @param : Taxtype taxtype
	 * @return : Taxtype createdTaxtype
	 * @description : For creating/inserting entry in taxtype table
	 */
	@Override
	public Taxtype createTaxtype(Taxtype taxtype) {
		logger.debug("Entering createTaxtype");
		Taxtype createdTaxtype = null;

		System.out.println(taxtype + "services*****************");
		createdTaxtype = taxtypeRepo.save(taxtype);
		logger.info("created Taxtype :" + createdTaxtype);
		return createdTaxtype;
	}

	/**
	 * @param : String taxtypeid
	 * @return : Taxtype taxtype
	 * @description : For get entry in taxtype table
	 */
	@Override
	public Taxtype getTaxtypeBytaxtypeid(int taxtypeid) {
		logger.debug("Entering getTaxtypeBytaxtypeid");

		Taxtype taxtype = taxtypeRepo.findByTaxTypeId(taxtypeid);
		logger.info("Founded taxtype :" + taxtype);

		return taxtype;
	}

	/**
	 * @return : List<Object> taxtype
	 * @description : For fetching all taxtype which are active state from taxtype
	 *              table
	 */
	@Override
	public List<Object> getAllTaxtypes() {
		logger.debug("Entering getAllTaxtypes");

		List<Taxtype> taxtypes = taxtypeRepo.findAll();
		List<Object> taxTypes = new ArrayList<>(taxtypes);
		logger.info("Fetched all active taxtype :" + taxtypes);
		return taxTypes;
	}

	/**
	 * @param : Taxtype to update
	 * @return : taxtype
	 * @description : For updating taxtype of taxtype table
	 */
	@Override
	public Taxtype updateTaxtypeBytaxtypeid(Taxtype taxtype, int taxtypeid) {
		logger.debug("Entering updateTaxtype");

		Taxtype toUpdatedTaxtype = null;
		Taxtype updatedTaxtype = null;

		toUpdatedTaxtype = taxtypeRepo.findByTaxTypeId(taxtypeid);

		logger.info("exisitng Taxtype :: " + toUpdatedTaxtype);

		if (toUpdatedTaxtype != null) {
			logger.debug("setting new data of Taxtype to exisitng Taxtype");

//			taxtype.setModifiedBy("admin");
			toUpdatedTaxtype.setTaxTypeName(taxtype.getTaxTypeName());
			toUpdatedTaxtype.setTaxTypeDescription(taxtype.getTaxTypeDescription());

			updatedTaxtype = taxtypeRepo.save(taxtype);

			logger.info("updated Taxtype :" + updatedTaxtype);
		}

		return updatedTaxtype;
	}

	/**
	 * @param : String taxtypeid
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of Taxtype
	 * 
	 */
	@Override
	public int deleteTaxtypeBytaxtypeid(int taxtypeid) {
		logger.debug("Entering deleteTaxtypeBytaxtypeid");

		int typeId = taxtypeRepo.deleteTaxTypeById(taxtypeid);
		// logger.info("deleted Taxtype count : " + count);
		return typeId;
	}

}
