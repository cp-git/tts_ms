package com.cpa.ttsms.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findByTokenUniqueID(String tokenUniqueID);

	Optional<RefreshToken> findByPassword(Optional<Password> findByUsername);
    
	@Modifying
	@Query(value = "DELETE FROM refreshtoken WHERE tokenuniqueid = ?1", nativeQuery = true)
	void deletByTokenUniqueId(String tokenuniqueid);

   
}
