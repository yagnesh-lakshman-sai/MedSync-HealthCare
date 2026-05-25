package com.medsync.builder;

import org.springframework.beans.BeanUtils;

import com.medsync.dto.StaffAddressDto;
import com.medsync.dto.StaffDetailsDto;
import com.medsync.model.Staff;
import com.medsync.model.StaffAddress;

public class StaffDtoBuilder {

	public static StaffDetailsDto buildStaffDetailsDto(Staff staff) {

		return StaffDetailsDto
				.builder()
				.staffId(staff.getStaffId())
				.firstName(staff.getFirstName())
				.lastName(staff.getLastName())
				.phoneNumber(staff.getPhoneNumber())
				.role(staff.getRole())
				.gender(staff.getGender())
				.experienceInYears(staff.getExperienceInYears())
				.dateOfJoining(staff.getDateOfJoining())
				.email(staff.getStaffDetails().getEmail())
				.specialization(staff.getSpecialization())
				.staffType(staff.getStaffType())
				.isEmployeeActive(staff.isEmployeeActive())
				.canLogin(staff.isCanLogin())
				.staffAddressDto(buildAddressDto(staff.getStaffAddress()))
				.build();


	}

	public static StaffAddressDto buildAddressDto(StaffAddress staffAddress) {

		StaffAddressDto staffAddressDto = new StaffAddressDto();

		BeanUtils.copyProperties(staffAddress, staffAddressDto);

		return staffAddressDto;
	}
	
	

}
