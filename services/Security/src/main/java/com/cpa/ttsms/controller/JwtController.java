package com.cpa.ttsms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.dto.AuthRequest;
import com.cpa.ttsms.dto.JwtResponse;
import com.cpa.ttsms.dto.RefreshTokenRequest;
import com.cpa.ttsms.entity.RefreshToken;
import com.cpa.ttsms.filter.JwtAuthFilter;
import com.cpa.ttsms.service.JwtService;
import com.cpa.ttsms.service.RefreshTokenService;
import com.cpa.ttsms.service.TokenHandler;


@RestController
@RequestMapping("/token")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    
    
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    
    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody String userName) {
    	System.out.println("Entered in geentrate token");
        String token = jwtService.generateToken(userName);
        return ResponseEntity.ok(token);
    }
    
    
    @GetMapping("/getEncryptedToken")
    public ResponseEntity<String> getEncryptedToken(@RequestParam("encryptedParam") String encryptedParam) throws Exception {
    	System.out.println("chkTok enter");
    	
    	System.out.println(encryptedParam);
    	String decryptedToken = TokenHandler.decrypt(encryptedParam);
    	
    	
    	System.out.println("decrypted token"+decryptedToken);
        if (decryptedToken != null && encryptedParam.startsWith("Bearer ")) {
            String token = encryptedParam.substring(7);
            if (jwtAuthFilter.validateToken(token)) {
                return ResponseEntity.ok("Token is valid");
            }
        }
        // Return error response if token is invalid or missing
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
    }
    @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken(@RequestHeader("Authorization") String authHeader) throws Exception {
    	System.out.println("chkTok enter");
//    	String decryptedToken = TokenHandler.decrypt(authHeader.substring(7));
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtAuthFilter.validateToken(token)) {
                return ResponseEntity.ok("Token is valid");
            }
        }
        // Return error response if token is invalid or missing
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
    }
    
    
    @PostMapping("/create-refresh-token")
    public RefreshToken createRefreshToken(@RequestParam String username) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);
        // Map RefreshToken to RefreshTokenDTO if needed
        return refreshToken;
    }
    
    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setAccessToken(jwtService.generateToken(authRequest.getUsername()));
            jwtResponse.setToken(refreshToken.getTokenUniqueID());
//            return JwtResponse.builder()
//                    .accessToken(jwtService.generateToken(authRequest.getUsername()))
//                    .token(refreshToken.getToken()).build();
            return jwtResponse;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
        @PostMapping("/refreshToken")
        public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
            JwtResponse jwtResponse = new JwtResponse();
            System.out.println("Inside refreshTokenS");
            System.out.println(refreshTokenRequest.toString());
            return refreshTokenService.findByToken(refreshTokenRequest.getTokenUniqueId())
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getPassword)
                    .map(userInfo -> {
                        String accessToken = jwtService.generateToken(userInfo.getUsername());
                        jwtResponse.setAccessToken(accessToken);
                        jwtResponse.setToken(refreshTokenRequest.getTokenUniqueId());
                        return jwtResponse; // Return JwtResponse here
                    })
                    .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
        }

}
