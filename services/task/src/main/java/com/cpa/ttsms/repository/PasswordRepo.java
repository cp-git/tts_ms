package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cpa.ttsms.entity.Password;

public interface PasswordRepo extends JpaRepository<Password, Integer> {

	/**
	 * Find a password in the database by its associated username.
	 *
	 * @param username The username associated with the password to search for.
	 * @return The Password entity if found, otherwise null.
	 */
	Password findByUsername(String username);
}
