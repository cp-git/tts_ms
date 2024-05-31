/**
 * @author - Code Generator
 * @createdOn 22-05-2024
 * @Description Controller class for ]chatmessage
 * 
 */

package com.cpa.ttsms.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cpa.ttsms.controller.chatmessageController;
import com.cpa.ttsms.entity.chatmessage;
import com.cpa.ttsms.repository.chatmessageRepo;
import com.cpa.ttsms.service.chatmessageService;

@Service
public class chatmessageServiceImpl implements chatmessageService {

	@Autowired
	private chatmessageRepo ]chatmessageRepo;
	private static Logger logger;

	public chatmessageServiceImpl() {
		logger = Logger.getLogger(chatmessageServiceImpl.class);
	}

	/**
	 * @param : chatmessage ]chatmessage
	 * @return : chatmessage createdchatmessage
	 * @description : For creating/inserting entry in chatmessage table
	 */
	@Override
	public chatmessage createchatmessage(chatmessage ]chatmessage) {
		logger.debug("Entering createchatmessage");
		chatmessage createdchatmessage = null;

	//	]chatmessage.setchatmessageCreatedBy("admin");
	//	]chatmessage.setchatmessageModifiedBy("admin");

		createdchatmessage = ]chatmessageRepo.save(]chatmessage);
		logger.info("created chatmessage :" + createdchatmessage);
		return createdchatmessage;
	}

	/**
	 * @param : String id
	 * @return : chatmessage ]chatmessage
	 * @description : For get entry in chatmessage table
	 */
	@Override
	public chatmessage getchatmessageByid(String id) {
		logger.debug("Entering getchatmessageByid");

		chatmessage ]chatmessage = ]chatmessageRepo.findByChatMessageid(id);
		logger.info("Founded ]chatmessage :" + ]chatmessage);

		return ]chatmessage;
	}

	/**
	 * @return : List<Object> ]chatmessage
	 * @description : For fetching all ]chatmessage which are active state from chatmessage table
	 */
	@Override
	public List<Object> getAllchatmessages() {
		logger.debug("Entering getAllchatmessages");

		List<Object> ]chatmessages = ]chatmessageRepo.findByChatMessageIsActiveTrue();
		logger.info("Fetched all active ]chatmessage :" + ]chatmessages);
		return ]chatmessages;
	}

	/**
	 * @param : chatmessage to update
	 * @return : ]chatmessage
	 * @description : For updating ]chatmessage of chatmessage table
	 */
	@Override
	public chatmessage updatechatmessageByid(chatmessage ]chatmessage, String id) {
		logger.debug("Entering updatechatmessage");

		chatmessage toUpdatedchatmessage = null;
		chatmessage updatedchatmessage = null;

		toUpdatedchatmessage = ]chatmessageRepo.findByChatMessageid(id);
		logger.info("exisitng chatmessage :: " + toUpdatedchatmessage);

		if (toUpdatedchatmessage != null) {
			logger.debug("setting new data of chatmessage to exisitng chatmessage");

//			]chatmessage.setModifiedBy("admin");
						
			updatedchatmessage = ]chatmessageRepo.save(]chatmessage);

			logger.info("updated chatmessage :" + updatedchatmessage);
		}

		return updatedchatmessage;
	}

	/**
	 * @param : String id
	 * @return : int (count of record updated)
	 * @description : This is function is used to soft delete the record of chatmessage
	 * 
	 */
	@Override
	public int deletechatmessageByid(String id) {
		logger.debug("Entering deletechatmessageByid");

		int count =  ]chatmessageRepo.deleteChatMessageByid(id);
		logger.info("deleted chatmessage count : " + count);
		return count;
	}

}
