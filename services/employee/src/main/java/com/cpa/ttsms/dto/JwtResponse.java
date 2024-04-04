package com.cpa.ttsms.dto;

public class JwtResponse {

    private String accessToken;
    private String tokenUniqueID;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public JwtResponse(String accessToken, String tokenUniqueID) {
		super();
		this.accessToken = accessToken;
		this.tokenUniqueID = tokenUniqueID;
	}
	public String getTokenUniqueID() {
		return tokenUniqueID;
	}
	public void setTokenUniqueID(String tokenUniqueID) {
		this.tokenUniqueID = tokenUniqueID;
	}
	public JwtResponse() {
		super();
	}
    
    
}
