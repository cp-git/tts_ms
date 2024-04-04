package com.cpa.ttsms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.RefreshToken;





public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByTokenUniqueID(String tokenUniqueID);
}
