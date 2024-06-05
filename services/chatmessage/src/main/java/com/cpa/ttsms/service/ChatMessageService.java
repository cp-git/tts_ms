package com.cpa.ttsms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cpa.ttsms.chatroom.ChatRoomService;
import com.cpa.ttsms.entity.ChatMessage;
import com.cpa.ttsms.repository.ChatMessageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ChatMessageService {
	
    private static final String UPLOAD_DIR = "/demo/tts";

    private  ChatMessageRepository repository;
	
    private ChatRoomService chatRoomService;

    public ChatMessageService(ChatMessageRepository repository) {
		super();
		this.repository = repository;
	}

    public ChatMessage save(ChatMessage chatMessage) {
        // Convert senderId and recipientId to lowercase
        String senderIdLower = chatMessage.getSenderId().toLowerCase();
        String recipientIdLower = chatMessage.getRecipientId().toLowerCase();
        
        // Create the chatId using the lowercase senderId and recipientId
        var chatId = senderIdLower + "_" + recipientIdLower;
        
        // Set the lowercase values back to the chatMessage object
        chatMessage.setSenderId(senderIdLower);
        chatMessage.setRecipientId(recipientIdLower);
        chatMessage.setChatId(chatId);
        
        // Save the chatMessage
        repository.save(chatMessage);
        
        return chatMessage;
    }


//    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
////        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
//      var chatId = senderId+"_"+recipientId;
//
////        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
//      return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
//
//    }
//    
//	public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
//        return repository.findChatMessagesBetweenUsers(senderId, recipientId);
//    }
//	
	
//	 public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
//	        // Find messages where senderId is senderId and recipientId is recipientId or vice versa
//		    List<ChatMessage> messages = new ArrayList<>();
//	        messages.addAll(repository.findMessagesFromSenderToRecipient(senderId, recipientId));
////	        messages.addAll(repository.findMessagesFromRecipientToSender(senderId, recipientId));
//	       System.out.println(messages);
//	        messages.addAll(repository.findMessagesFromSenderToRecipient(recipientId, senderId));
//
//	        System.out.println(messages);
//	        return messages;
//	 
//	 
//	 }
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        return repository.findMessagesBetweenUsers(senderId, recipientId);
    }
    
    public void  findChatMessagesandUpdateNotification(String senderId, String recipientId) {
        // Retrieve the chat ID
        Optional<String> chatId = chatRoomService.getChatRoomId(senderId, recipientId, true);

        // Find chat messages by chat ID or return an empty list
        List<ChatMessage> chatMessages = chatId.map(repository::findByChatId).orElse(new ArrayList<>());

        // Set the notification field to true for each chat message
        chatMessages.forEach(chatMessage -> chatMessage.setNotification(false));
        System.out.println("chat message updation");
        System.out.println(chatMessages.toString());
        // Save the updated chat messages back to the repository
        repository.saveAll(chatMessages);

        
    }
    
    
    public void updateNotificationsForUsers(String senderId, String recipientId) {
       repository.updateNotificationToFalse(senderId, recipientId);
    }
//    public List<String> findChatMessagesByrecipientId(String recipientId) {
//    	List<ChatMessage> chatMessageObjs = repository.findByRecipientIdAndNotification(recipientId, true);
//    	List<String> senderIds = chatMessageObjs.stream()
//                .map(ChatMessage::getSenderId)
//                .collect(Collectors.toList());
//    	return senderIds;
//    }
    
    
    public List<ChatMessage> getUserIdsByRecentNotification(String recipientId) {
        List<ChatMessage> chatMessageObjs = repository.findByRecipientIdAndNotificationOrderByTimestampAsc(recipientId, true);
//        List<String> senderIds = chatMessageObjs.stream()
//                .map(ChatMessage::getSenderId)
//                .distinct() // Ensure the list contains unique sender IDs
//                .collect(Collectors.toList());
        return chatMessageObjs;
    }
    
    
    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload empty file");
        }

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, bytes);

        return path.toString();
    }

    public byte[] getFile(String filename) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + filename);
        return Files.readAllBytes(path);
    }
    
 // Scheduled method to delete messages older than 2 days
//    @Scheduled(fixedRate = 86400000) // Executes once every day (24 hours * 60 minutes * 60 seconds * 1000 milliseconds)    @Transactional
//     @Transactional
    
//    public void deleteOldMessages() {
//       
//        System.out.println("Running scheduled task to delete old messages");
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -2);
//        Date twoDaysAgo = calendar.getTime();
//        repository.deleteByTimestampBefore(twoDaysAgo);
////        repository.deleteByTimestamp(twoDaysAgo);
//        System.out.println("Old messages deleted");
//    }
    
    
    
//    @Scheduled(fixedRate = 60000) // Schedule to run every 1 minute
//    @Transactional
//    public void deleteOldMessages() {
//        System.out.println("Running scheduled task to delete old messages");
//        
//        // Calculate the timestamp for 1 minute ago
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, -1);
//        Date oneMinuteAgo = calendar.getTime();
//        
//        // Delete messages before the calculated timestamp
//        repository.deleteByTimestampBefore(oneMinuteAgo);
//        
//        System.out.println("Old messages deleted");
//    }
}
