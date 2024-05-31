package com.cpa.ttsms.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cpa.ttsms.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);
    
    // Define the method to delete messages older than a specified timestamp
    void deleteByTimestampBefore(Date timestamp);
    
    // Add method to find messages by recipientId and notification, ordered by timestamp descending
    List<ChatMessage> findByRecipientIdAndNotificationOrderByTimestampAsc(String recipientId, boolean notification);
    
    
    @Query("SELECT m FROM ChatMessage m WHERE (m.senderId = :senderId AND m.recipientId = :recipientId) OR (m.senderId = :recipientId AND m.recipientId = :senderId)")
    List<ChatMessage> findChatMessagesBetweenUsers(@Param("senderId") String senderId, @Param("recipientId") String recipientId);

    
    @Query("SELECT m FROM ChatMessage m WHERE m.senderId = :senderId AND m.recipientId = :recipientId")
    List<ChatMessage> findMessagesFromSenderToRecipient(@Param("senderId") String senderId, @Param("recipientId") String recipientId);
   
//   @Query("SELECT m FROM ChatMessage m WHERE (m.senderId = :senderId AND m.recipientId = :recipientId) " +
//            "OR (m.senderId = :recipientId AND m.recipientId = :senderId) " +
//            "ORDER BY m.timestamp")
//     List<ChatMessage> findMessagesBetweenUsers(@Param("senderId") String senderId, @Param("recipientId") String recipientId);
// 

    @Query(value = "SELECT * FROM ChatMessage WHERE (senderId = :senderId AND recipientId = :recipientId) " +
            "OR (senderId = :recipientId AND recipientId = :senderId) " +
            "ORDER BY timestamp ASC", nativeQuery = true)
List<ChatMessage> findMessagesBetweenUsers(@Param("senderId") String senderId, @Param("recipientId") String recipientId);

    
    @Query("SELECT c FROM ChatMessage c WHERE c.senderId = :senderId AND c.recipientId = :recipientId AND c.notification = true ORDER BY c.timestamp ASC")
    List<ChatMessage> findBySenderIdAndRecipientIdAndNotificationTrueOrderByTimestampAsc(@Param("senderId") String senderId, @Param("recipientId") String recipientId);

    
    @Modifying
    @Transactional
    @Query("UPDATE ChatMessage c SET c.notification = false WHERE c.senderId = :senderId AND c.recipientId = :recipientId")
    int updateNotificationToFalse(@Param("senderId") String senderId, @Param("recipientId") String recipientId);

    @Modifying
    @Transactional
    @Query("UPDATE ChatMessage cm SET cm.notification = :status WHERE cm.senderId = :username OR cm.recipientId = :username")
    int updateNotificationStatusByUsername(boolean status, String username);
}
