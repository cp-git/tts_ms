package com.cpa.ttsms.controller;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import com.cpa.ttsms.entity.ChatMessage;
import com.cpa.ttsms.service.ChatMessageService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class ChatController {

//    private SimpMessagingTemplate messagingTemplate;

	//    @Autowired
//    private  ChatMessageService chatMessageService;

	private final SimpMessagingTemplate messagingTemplate;
	
    private final ChatMessageService chatMessageService;

  
    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;

        
    }
    
    @MessageMapping("/chat")
//    @SendTo("/topic/messages")  
 public ChatMessage processMessage(@Payload ChatMessage chatMessage) throws InterruptedException {
    	
    	System.out.println(chatMessage.getRecipientId());
    	System.out.println(chatMessage.getSenderId());

        ChatMessage savedMsg = chatMessageService.save(chatMessage);
   
        messagingTemplate.convertAndSend("/user/"+chatMessage.getRecipientId()+"/queue/messages",chatMessage );
    
        messagingTemplate.convertAndSend("/user/"+chatMessage.getSenderId()+"/queue/messages",chatMessage );
        Thread.sleep(2000);
        return chatMessage;

//        messagingTemplate.convertAndSendToUser(
//        	
//                chatMessage.getRecipientId(), "/user/"+chatMessage.getRecipientId()+"/queue/messages",
//                new ChatNotification(
//                        savedMsg.getId(),
//                        savedMsg.getSenderId(),
//                        savedMsg.getRecipientId(),
//                        savedMsg.getContent()
//                ),createHeaders(chatMessage.getRecipientId())); // Pass recipient's session ID as headers
//
//       messagingTemplate.convertAndSendToUser(
//                       chatMessage.getSenderId(),"/user/"+  chatMessage.getSenderId()+ "/queue/messages",
//                        new ChatNotification(
//                                savedMsg.getId(),
//                                savedMsg.getSenderId(),
//                                savedMsg.getRecipientId(),
//                                savedMsg.getContent()
//                        ),createHeaders(chatMessage.getSenderId()) );
        
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(false);
        return headerAccessor.getMessageHeaders();
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
                                                 @PathVariable String recipientId) {
    	
    	
    	senderId = senderId.toLowerCase();
    	recipientId = recipientId.toLowerCase();
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
  
    @PatchMapping("/updateNotification/{senderId}/{recipientId}")
    public ResponseEntity<String> updateNotification(@PathVariable String senderId, @PathVariable String recipientId) {
        try {
        	senderId = senderId.toLowerCase();
        	recipientId = recipientId.toLowerCase();
            chatMessageService.updateNotificationsForUsers(senderId, recipientId);
            return ResponseEntity.ok("Notification updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating notification: " + e.getMessage());
        }
    }

    @GetMapping("/senderIds/{recipientId}")
    public ResponseEntity<List<ChatMessage>> getRecentUserIds(@PathVariable String recipientId) {
        try {
            List<ChatMessage> userIds = chatMessageService.getUserIdsByRecentNotification(recipientId);
            return ResponseEntity.ok(userIds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/chatpage")
    public String chatPage(@RequestParam(name = "userId") String userId) {
        // You can use the userId parameter here as needed
        System.out.println("User ID: " + userId);
        
        // Assuming your chat.html file is located in src/main/resources/templates
        return "chat.html";
    }
    
    @MessageMapping("/hii")
//    @SendTo("/user/public")
  
    public String greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        
        return new String("Hello, " + HtmlUtils.htmlEscape(message) + "!");
    }
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("chatId") String chatId,
            @RequestParam("senderId") String senderId,
            @RequestParam("recipientId") String recipientId,
            @RequestParam("content") String content,
            @RequestParam("notification") boolean notification)
    {
        try {
            String fileUrl = chatMessageService.saveFile(file);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setChatId(chatId);
            chatMessage.setSenderId(senderId);
            chatMessage.setRecipientId(recipientId);
            chatMessage.setContent(content);
            chatMessage.setNotification(notification);
            chatMessage.setFileUrl(fileUrl);

            chatMessageService.save(chatMessage);

            return ResponseEntity.ok("File uploaded and message saved successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            byte[] data = chatMessageService.getFile(filename);
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(data.length)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/setUserId")
    public ResponseEntity<String> setUserId(@RequestParam String senderId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("USER_ID", senderId);
        String userId = (String) session.getAttribute("USER_ID");
        System.out.println("User ID set in session: " + userId);
        return ResponseEntity.ok(userId);
    }
    
    
//    @MessageMapping("/user.addUser")
//    @SendTo("/topic/public")
//    public User addUser(
//            @Payload User user
//    ) throws InterruptedException {
//    	System.out.println("INSIDE addUser");
//        System.out.println("User Nickname: " + user.getNickName());
//        System.out.println("User Full Name: " + user.getFullName());
//        userService.saveUser(user);
//        Thread.sleep(1000); // simulated delay
//
//        return user;
//    }
//
//    @MessageMapping("/user.disconnectUser")
//    @SendTo("/topic/public")
//    public User disconnectUser(
//            @Payload User user
//    ) throws InterruptedException {
//    	
//    	System.out.println("Inside disconnectUser");
//    	System.out.println(user);
//        userService.disconnect(user);
//        Thread.sleep(1000); // simulated delay
//
//        return user;
//    }

}
