package com.cpa.jwt.FilterService.filter;

import java.io.IOException;



import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;


public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servetRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	    final HttpServletRequest request =(HttpServletRequest) servetRequest;
        final HttpServletResponse  response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");
        if ("OPTIONS".equals(request.getMethod())) {
        	System.out.println("Entered here");
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                throw new ServletException("An exception occurred");
            }
        }
        final String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        request.setAttribute("claims", claims);
        request.setAttribute("blog", servetRequest.getParameter("id"));
        filterChain.doFilter(request, response);
    }
		
}


