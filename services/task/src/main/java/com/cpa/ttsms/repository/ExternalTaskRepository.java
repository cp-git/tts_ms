package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.ExternalTask;

@Repository
public interface ExternalTaskRepository extends JpaRepository<ExternalTask, Integer> {

}
