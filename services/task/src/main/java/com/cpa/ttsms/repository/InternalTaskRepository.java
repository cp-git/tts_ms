package com.cpa.ttsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.InternalTask;

public interface InternalTaskRepository extends JpaRepository<InternalTask, Integer> {

	public InternalTask findByTaskId(int taskId);

	public List<InternalTask> findByBenchCandidateId(int candidateId);
}
