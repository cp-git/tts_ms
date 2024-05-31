/**
 * @author  - Code Generator
 * @createdOn -  22-05-2024
 * @Description Entity class for chatmessage Service
 * 
 */

package com.cpa.ttsms.service;

import java.util.List;

import com.cpa.ttsms.entity.chatmessage;

public interface chatmessageService {

	chatmessage createchatmessage(chatmessage ]chatmessage);

	chatmessage getchatmessageByid(String id);

	List<Object> getAllchatmessages();

	chatmessage updatechatmessageByid(chatmessage ]chatmessage, String id);

	int deletechatmessageByid(String id);

}