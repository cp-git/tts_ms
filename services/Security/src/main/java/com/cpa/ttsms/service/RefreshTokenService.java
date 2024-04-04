package com.cpa.ttsms.service;

import java.time.Instant;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.entity.RefreshToken;
import com.cpa.ttsms.repository.PasswordRepo;
import com.cpa.ttsms.repository.RefreshTokenRepository;



@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    
    @Autowired
    private PasswordRepo userInfoRepository;


    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken() ;
      
	    refreshToken.setPassword(userInfoRepository.findByUsername(username).get());

                 refreshToken.setTokenUniqueID(UUID.randomUUID().toString());
               refreshToken.setExpiryDate(Instant.now().plusMillis(60000*2));//10
                
        return refreshTokenRepository.save(refreshToken);
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByTokenUniqueID(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getTokenUniqueID() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

}