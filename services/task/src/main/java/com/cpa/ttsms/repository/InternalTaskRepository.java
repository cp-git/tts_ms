package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.InternalTask;

public interface InternalTaskRepository extends JpaRepository<InternalTask, Integer> {

	public InternalTask findByTaskId(int taskId);
}
