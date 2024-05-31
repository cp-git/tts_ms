/**
 * @author  - Code Generator
 * @createdOn -  22-05-2024
 * @Description Entity class for chatmessage
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.chatmessage;

@Repository
public interface chatmessageRepo extends JpaRepository<chatmessage, Integer> {

	public chatmessage findByChatMessageid(String id);

	public List<Object> findByChatMessageIsActiveTrue();

	@Transactional
	@Modifying
	@Query(value = "UPDATE chatmessage SET is_active=false WHERE id = ?1", nativeQuery = true)
	public int deleteChatMessageByid(String id);

}
