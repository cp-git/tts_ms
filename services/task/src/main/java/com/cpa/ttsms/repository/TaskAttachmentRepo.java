package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.TaskAttachment;

@Repository
public interface TaskAttachmentRepo extends JpaRepository<TaskAttachment, Integer> {

}
