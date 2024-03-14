package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.TaskAttachment;

@Repository
public interface TaskAttachmentRepo extends JpaRepository<TaskAttachment, Integer> {

	@Modifying
	@Query(value = "DELETE FROM public.taskattachment ta where ta.taskid=?1 AND ta.filename=?2", nativeQuery = true)
	public int deleteFileByTaskIdAndFilename(int taskId, String fileName);

	public TaskAttachment findByTaskIDAndFileName(int taskId, String fileName);
}
