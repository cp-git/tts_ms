/**
 * @author  - Code Generator
 * @createdOn -  07-08-2023
 * @Description Entity class for Reason
 * 
 */

package com.cpa.ttsms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cpa.ttsms.entity.Reason;

@Repository
public interface ReasonRepo extends JpaRepository<Reason, Integer> {

}
