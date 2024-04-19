package com.cpa.ttsms.service;

import java.time.Instant;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${refresh-token-expiry-millis}")
    private long refreshTokenExpiryMillis;
    
    @Autowired
    private PasswordRepo userInfoRepository;


    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken() ;
      
        System.out.println(username);
	    refreshToken.setPassword(userInfoRepository.findByUsername(username).get());

               refreshToken.setTokenUniqueID(UUID.randomUUID().toString());
               refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenExpiryMillis));//10

                
        return refreshTokenRepository.save(refreshToken);
    }


    public Optional<RefreshToken> findByToken(String token) {
    	
    	Optional<RefreshToken> refreshOBJ = refreshTokenRepository.findByTokenUniqueID(token);
    	System.out.println("Inside repo");
    	System.out.println(refreshOBJ);
        return refreshTokenRepository.findByTokenUniqueID(token);
    }

    public Optional<RefreshToken> findByUsername(String username) {
        return refreshTokenRepository.findByPassword(userInfoRepository.findByUsername(username));
    }
    
    @Transactional
    public boolean deletebyTokenUniqueID(String token) {
        
        Optional<RefreshToken> optionalToken = refreshTokenRepository.findByTokenUniqueID(token);
        if (optionalToken.isPresent()) {
            refreshTokenRepository.deletByTokenUniqueId(token);
            System.out.println("Token deleted successfully");
            return true;
        } else {
            System.out.println("Token not found");
            return false;
        }
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getTokenUniqueID() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

}