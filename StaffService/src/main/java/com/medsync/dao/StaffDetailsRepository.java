package com.medsync.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medsync.model.Staff;
import com.medsync.model.StaffDetails;

public interface StaffDetailsRepository extends JpaRepository<StaffDetails, Long> {

	Optional<StaffDetails> findByEmail(String email);
	
}
