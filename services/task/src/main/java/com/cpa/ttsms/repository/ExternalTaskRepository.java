package com.cpa.ttsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.ExternalTask;

@Repository
public interface ExternalTaskRepository extends JpaRepository<ExternalTask, Integer> {

	public ExternalTask findByTaskId(int taskId);

	public List<ExternalTask> findByHiringCompanyId(int companyId);
}
