package com.medsync.builder;

import org.springframework.beans.BeanUtils;

import com.medsync.dto.PatientAddressRequestDto;
import com.medsync.dto.RegisterPatientRequestDto;
import com.medsync.model.Patient;
import com.medsync.model.PatientAddress;

public class PatientBuilder {
	
	public static Patient buildPatientFromRegisterPatientRequestDto(RegisterPatientRequestDto registerPatientRequestDto) {
		
		return Patient.builder()
				.patientName(registerPatientRequestDto.getPatientName() )
				.gender(registerPatientRequestDto.getGender())
				.patientEmail(registerPatientRequestDto.getPatientEmail())
				.patientPhoneNumber(registerPatientRequestDto.getPatientPhoneNumber())
				.dateOfBirth(registerPatientRequestDto.getDateOfBirth())
				.patientAddress(buildPatientAddressFromPatientAddressRequestDto(registerPatientRequestDto.getPatientAddress()))
				.build();
	}
	
	private static PatientAddress buildPatientAddressFromPatientAddressRequestDto(PatientAddressRequestDto patientAddressRequestDto){
		PatientAddress patientAddress = new PatientAddress();
		BeanUtils.copyProperties(patientAddressRequestDto, patientAddress);
		return patientAddress;
	}

}
