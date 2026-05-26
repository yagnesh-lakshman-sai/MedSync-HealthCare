package com.medsync.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medsync.model.Staff;

import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

@Component
public class StaffEntityListener {

	public static StaffIdGenerator staffIdGenerator;

	@Autowired
	public void init(StaffIdGenerator staffIdGenerator) {

		this.staffIdGenerator = staffIdGenerator;
	}
	
	 @PrePersist
	    public void generateStaffId(Staff staff) {
	        if (staff.getStaffId()==null || staff.getStaffId().isEmpty()) {
	           staff.setStaffId(staffIdGenerator.generateNextStaffId()); 
	        }
	 }
	    
}
