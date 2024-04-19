package com.cpa.ttsms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpa.ttsms.dto.AuthRequest;
import com.cpa.ttsms.dto.JwtResponse;
import com.cpa.ttsms.dto.RefreshTokenRequest;
import com.cpa.ttsms.entity.RefreshToken;
import com.cpa.ttsms.filter.JwtAuthFilter;
import com.cpa.ttsms.repository.RefreshTokenRepository;
import com.cpa.ttsms.service.JwtService;
import com.cpa.ttsms.service.RefreshTokenService;
import com.cpa.ttsms.service.TokenHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.impl.DefaultClaims;

@CrossOrigin(origins = "http://localhost:4200")
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
    private RefreshTokenRepository refreshTokenRepository;

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
//            if (jwtAuthFilter.validateToken(token)) {
//                return ResponseEntity.ok("Token is valid");
//            }
            try {
                if (jwtAuthFilter.validateToken(token)) {
                    return ResponseEntity.ok("Token is valid");
                }
            } catch (ExpiredJwtException ex) {
                // Handle expired JWT exception
                System.out.println("Token has Expired in catch block");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
            } catch (Exception ex) {
                // Handle other exceptions
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        }
        // Return error response if token is invalid or missing
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing token");
    }
    
    
    @PostMapping("/create-refresh-token")
    public RefreshToken createRefreshToken(@RequestParam String username) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);
        // Map RefreshToken to RefreshTokenDTO if needed
        System.out.println(refreshToken);
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
//        @PostMapping("/refreshToken")
//        public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
//            JwtResponse jwtResponse = new JwtResponse();
//            System.out.println("Inside refreshTokenS");
//            System.out.println(refreshTokenRequest.getTokenUniqueId());
//            return refreshTokenService.findByToken(refreshTokenRequest.getTokenUniqueId())
////                    .map(refreshTokenService::verifyExpiration)
//                    .map(RefreshToken::getPassword)
//                    .map(userInfo -> {
//                    	System.out.println("userInfo Object");
//                    	System.out.println(userInfo);
//                        String accessToken = jwtService.generateToken(userInfo.getUsername());
//                        jwtResponse.setAccessToken(accessToken);
//                        jwtResponse.setToken(refreshTokenRequest.getTokenUniqueId());
//                        return jwtResponse; // Return JwtResponse here
//                    })
//                    .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
//        }
        
    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest,HttpServletRequest request) {
        JwtResponse jwtResponse = new JwtResponse();
        System.out.println("Inside refreshTokenS");
        System.out.println(refreshTokenRequest.getTokenUniqueId());
        
        // Find the RefreshToken object by tokenUniqueId
        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequest.getTokenUniqueId())
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));

        // Update the tokenUniqueId to a new value or generate a new value
        String newTokenUniqueId = UUID.randomUUID().toString(); // You need to implement this method

        // Update the RefreshToken object with the new tokenUniqueId
        refreshToken.setTokenUniqueID(newTokenUniqueId);

        // Save the updated RefreshToken object back to the database
        refreshTokenRepository.save(refreshToken); // You need to implement this method if it's not already implemented

        // Generate a new access token using the updated tokenUniqueId
//        String accessToken = jwtService.generateToken(refreshToken.getPassword().getUsername());
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtService.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        // Set the new access token and tokenUniqueId in the JwtResponse
        jwtResponse.setAccessToken(token);
        jwtResponse.setToken(newTokenUniqueId);

        return jwtResponse;
    }

        @DeleteMapping("/deleteTokenId/{tokenId}")
        public boolean deleteEmployeeByEmployeeId(@PathVariable("tokenId") String tokenId) {
            // Log the entry of the method
            try {
                // Attempt to delete the token using the refreshTokenService
                boolean isDeleted = refreshTokenService.deletebyTokenUniqueID(tokenId);
                
                if (isDeleted) {
                    return isDeleted; // Return 200 OK if deletion is successful
                } else {
                    return false; // Return 404 Not Found if deletion fails
                }
            } catch (Exception ex) {
                throw new RuntimeException("Token Entry Not Deleted", ex); // Throw a RuntimeException with appropriate message and cause
            }
        }

        @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
    	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
    		// From the HttpRequest get the claims
        	System.out.println("Request value " +request);
    		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

    		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
    		String token = jwtService.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
    		System.out.println(token);
    		return ResponseEntity.ok(null);
    	}

        public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
    		Map<String, Object> expectedMap = new HashMap();
    		for (Entry<String, Object> entry : claims.entrySet()) {
    			expectedMap.put(entry.getKey(), entry.getValue());
    		}
    		return expectedMap;
    	}
}
