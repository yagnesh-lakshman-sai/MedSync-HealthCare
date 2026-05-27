package com.medsync.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.medsync.dto.LoginRequest;
import com.medsync.dto.LoginResponse;
import com.medsync.dto.RegisterStaffDto;
import com.medsync.dto.ResetPasswordRequest;
import com.medsync.dto.StaffDetailsDto;
import com.medsync.dto.VerifyOtpRequest;
import com.medsync.model.Staff;

public interface StaffService {
	
	 Staff getStaffByStaffId(String staffId);

	 ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(String name);
	 
	 StaffDetailsDto updateStaff(String staffId,RegisterStaffDto dto);
	
	 StaffDetailsDto registerStaffDeatils(RegisterStaffDto registerStaffDto);

	 String deleteStaff(String staffId);

	 String getDoctorName(String doctorId);
	 
	 String getSpecialization(String staffId);

	 List<StaffDetailsDto> getAllStaff();

	 void sendOtp(String email);

	 void verifyOtp(VerifyOtpRequest request);

	 void resetPassword(ResetPasswordRequest request);  
	 
	 LoginResponse login(LoginRequest request);
		
}

