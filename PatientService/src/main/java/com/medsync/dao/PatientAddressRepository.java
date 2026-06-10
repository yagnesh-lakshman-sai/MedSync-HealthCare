package com.medsync.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medsync.model.PatientAddress;

@Repository
public interface PatientAddressRepository extends JpaRepository<PatientAddress, Long> {

}
