package com.cpa.ttsms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;

import com.cpa.ttsms.config.UserInfoUserDetailsService;
import com.cpa.ttsms.entity.Password;
import com.cpa.ttsms.service.JwtService;
import com.cpa.ttsms.service.RefreshTokenService;

import io.jsonwebtoken.ExpiredJwtException;



@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException,ExpiredJwtException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        

        try {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
          	System.out.println("Usrname in if block" +username);
        }
 
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        }
            catch (ExpiredJwtException ex) {
                // Handle expired JWT exception
            	System.out.println("Token has Expired");
            	System.out.println("Usrname in catch" +username);
//            	refreshTokenService.createRefreshToken(username);
            	String isRefreshToken = request.getHeader("isRefreshToken");
    			String requestURL = request.getRequestURL().toString();
    			// allow for Refresh Token creation if following conditions are true.
    			if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
    				System.out.println("Enterd in if");
    				allowForRefreshToken(ex, request);
    			} else
    				request.setAttribute("exception", ex);

                
            }
        
        filterChain.doFilter(request, response);
    }


//    public boolean validateToken(String token)  throws ExpiredJwtException{
//    	System.out.println("entered in validateToken");
//        String username = jwtService.extractUsername(token);
//        System.out.println();
//        try {
//        if (username != null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            if (jwtService.validateToken(token, userDetails)) {
//            	System.out.println("Entered in if loop");
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
//                System.out.println("After UsernamePasswordAuthenticationToken");
//                System.out.println("After authtoken");
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//                return true; // Token is valid
//            }
//        }
//        }
//        catch (ExpiredJwtException ex) {
//            // Handle expired JWT exception
//        	System.out.println("Token has Expired");
//            
//        }
//        return false; // Token is invalid
//    }
    public boolean validateToken(String token) throws ExpiredJwtException {
        System.out.println("entered in validateToken");
        String username = jwtService.extractUsername(token);
        System.out.println();
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                System.out.println("Entered in if loop");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                System.out.println("After UsernamePasswordAuthenticationToken");
                System.out.println("After authtoken");
                SecurityContextHolder.getContext().setAuthentication(authToken);
                return true; // Token is valid
            }
        }
        // Token is invalid or expired, throw ExpiredJwtException
        throw new ExpiredJwtException(null, null, "Token has expired in validate token");
    }


    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

    	System.out.println("allowForRefreshToken called");
		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}
}