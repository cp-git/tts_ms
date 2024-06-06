package com.cpa.ttsms.chatroom;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ChatRoomService {

	
    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
		super();
		this.chatRoomRepository = chatRoomRepository;
	}

	private  ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists) {
                    	System.out.println("entered in if statement");
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }

                    return  Optional.empty();
                });
    }

//    private String createChatId(String senderId, String recipientId) {
//        var chatId = String.format("%s_%s", senderId, recipientId);
//
//		ChatRoom senderRecipient = new ChatRoom();
//		senderRecipient.setChatId(chatId);
//		senderRecipient.setSenderId(senderId);
//		senderRecipient.setRecipientId(recipientId);
//
//		ChatRoom recipientSender = new ChatRoom();
//		recipientSender.setChatId(chatId);
//		recipientSender.setSenderId(recipientId);
//		recipientSender.setRecipientId(senderId);
//
//		// Save the ChatRoom instances
//		chatRoomRepository.save(senderRecipient);
//		chatRoomRepository.save(recipientSender);
//
//        chatRoomRepository.save(senderRecipient);
//        chatRoomRepository.save(recipientSender);
//
//        return chatId;
//    }
    
    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = new ChatRoom();
        senderRecipient.setChatId(chatId);
        senderRecipient.setSenderId(senderId);
        senderRecipient.setRecipientId(recipientId);

        ChatRoom recipientSender = new ChatRoom();
        recipientSender.setChatId(chatId);
        recipientSender.setSenderId(recipientId);
        recipientSender.setRecipientId(senderId);

        // Save the ChatRoom instances
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }

}
