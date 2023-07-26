/**
 * @author  - Code Generator
 * @createdOn -  25-07-2023
 * @Description Entity class for Task
 * 
 */

package com.cpa.ttsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {

	public Task findByTaskId(int id);

	public List<Object> findByTaskIsActiveTrue();

	@Transactional
	@Modifying
	@Query(value = "UPDATE task SET is_active=false WHERE id = ?1", nativeQuery = true)
	public int deleteTaskById(String id);

	List<Task> findByTaskParentIsNullAndTaskStatus(String status);

	List<Task> findByTaskParentIsNullAndTaskStatusNotIn(String... statuses);

	List<Task> findByTaskParent(int parentId);

}
