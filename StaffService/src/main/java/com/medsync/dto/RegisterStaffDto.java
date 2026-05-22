package com.medsync.dto;

import java.time.LocalDate;

import com.medsync.enums.Specialization;
import com.medsync.enums.StaffType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStaffDto {
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String role;
	
	private String gender;
	
	private LocalDate dateOfJoining;
	
	private int experienceInYears;
	
	private String email;
	
	private StaffAddressDto staffAddressDto;
	
	private Specialization specialization;
	
	private StaffType staffType;
	

}
