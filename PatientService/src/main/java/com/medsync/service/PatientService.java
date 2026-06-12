package com.medsync.service;

import java.time.LocalDate;
import java.util.List;

import com.medsync.dto.RegisterPatientRequestDto;
import com.medsync.dto.RegisterPatientResponseDto;
import com.medsync.model.Patient;

public interface PatientService {
	
	List<RegisterPatientResponseDto> getAllPatients();

	RegisterPatientResponseDto regiesterPatient(RegisterPatientRequestDto registerPatientRequestDto);
	
	RegisterPatientResponseDto updatePatient(RegisterPatientRequestDto patientRequestDto ,String patientId);
	
	RegisterPatientResponseDto getPatientById(String patientId);

	List<Patient> getPatientsByDoctor(List<String> listOfPatientIds);

	List<String> getPatientsVisitedByDoctor(String staffId, LocalDate startDate, LocalDate endDate);

	String getPatientName(String patientId);
	
}
