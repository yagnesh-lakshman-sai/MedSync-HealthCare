package com.medsync.dto;

import java.time.LocalDate;

import com.medsync.enums.Specialization;
import com.medsync.enums.StaffType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDetailsDto {
	
	private String staffId;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String role;
	
	private String gender;
	
	private LocalDate dateOfJoining;
	
	private int experienceInYears;
	
	private String email;
	
	private Specialization specialization;
	
	private StaffType staffType;
	
	private boolean isEmployeeActive;
	
	private boolean canLogin;
	
	private StaffAddressDto staffAddressDto;
	

}
