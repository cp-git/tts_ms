package com.cpa.ttsms.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class RefreshToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tokenUniqueID;

    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "user_name", referencedColumnName = "username")
    private Password password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getTokenUniqueID() {
		return tokenUniqueID;
	}

	public void setTokenUniqueID(String tokenUniqueID) {
		this.tokenUniqueID = tokenUniqueID;
	}

	public Instant getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}



	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	
	public RefreshToken(int id, String tokenUniqueID, Instant expiryDate, Password password) {
		super();
		this.id = id;
		this.tokenUniqueID = tokenUniqueID;
		this.expiryDate = expiryDate;
		this.password = password;
	}

	public RefreshToken() {
		super();
	}
    
    
}
