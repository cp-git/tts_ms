package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.CompanyPhotos;

public interface CompanyPhotosRepo extends JpaRepository<CompanyPhotos, Integer> {
}