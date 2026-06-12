package com.medsync.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.medsync.builder.PatientBuilder;
import com.medsync.clients.AppointmentClient;
import com.medsync.dao.PatientRepository;
import com.medsync.dto.RegisterPatientRequestDto;
import com.medsync.dto.RegisterPatientResponseDto;
import com.medsync.dto.builder.PatientDTOBuilder;
import com.medsync.exception.PatientNotFoundException;
import com.medsync.model.Patient;
import com.medsync.service.PatientService;
import com.medsync.utils.PatientIdGenerator;

@Service
public class PatientServiceImpl implements PatientService {

	public final PatientRepository patientRepository;

	public final PatientIdGenerator patientIdGenerator;
	
	public final AppointmentClient appointmentClient;

	public PatientServiceImpl(PatientRepository patientRepository, PatientIdGenerator patientIdGenerator, AppointmentClient appointmentClient) {
	
		this.patientRepository = patientRepository;
		
		this.patientIdGenerator = patientIdGenerator;
		
		this.appointmentClient = appointmentClient;
	}

	@Override
	public RegisterPatientResponseDto regiesterPatient(RegisterPatientRequestDto registerPatientRequestDto) {
		
		Patient patient = PatientBuilder.buildPatientFromRegisterPatientRequestDto(registerPatientRequestDto);

		Patient registedPatient = patientRepository.save(patient);

		return PatientDTOBuilder.fromPatientEntityToRegPatientRespDtO(registedPatient);
	}

	@Override
	public RegisterPatientResponseDto updatePatient(RegisterPatientRequestDto patientRequestDto,
			String patientId) {
		
		Patient existingPatient= patientRepository.findById(patientId).orElseThrow(()->new PatientNotFoundException("No patient found with ID "+patientId));
		
		Patient updatedPatient=PatientBuilder.buildPatientFromRegisterPatientRequestDto(patientRequestDto);
		
		updatedPatient.setPatientId(patientId);
		
		if(existingPatient.getPatientAddress()!=null && updatedPatient.getPatientAddress() !=null) {
			
			updatedPatient.getPatientAddress().setPatientAddressId(existingPatient.getPatientAddress().getPatientAddressId());
		}
		
		Patient savedPatient= patientRepository.save(updatedPatient);
		
		return PatientDTOBuilder.fromPatientEntityToRegPatientRespDtO(savedPatient);
		
	}

	@Override
	public RegisterPatientResponseDto getPatientById(String patientId) {
		// TODO Auto-generated method stub
		
		Patient patient=patientRepository.findById(patientId).orElseThrow(()->new PatientNotFoundException("No patient found with ID "+patientId));
		
		return PatientDTOBuilder.fromPatientEntityToRegPatientRespDtO(patient);
	}

	@Override
	public List<Patient> getPatientsByDoctor(List<String> listOfPatientIds) {
		
		List<Patient> patients = patientRepository.findBypatientIdIn(listOfPatientIds);

		return patients;
	}

	@Override
	public List<String> getPatientsVisitedByDoctor(String staffId, LocalDate startDate, LocalDate endDate) {
		
        String startdate = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		String enddate = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		return appointmentClient.getPatientsVisitedByDoctor(staffId, startdate, enddate);
	}
		
	@Override	
	public String getPatientName(String patientId) {
		
		Patient patient=patientRepository.findById(patientId).orElseThrow(()->new PatientNotFoundException("No patient found with ID "+patientId));
		
		return patient.getPatientName();
	}

	@Override
	public List<RegisterPatientResponseDto> getAllPatients() {
		
		List<Patient> allPatients = patientRepository.findAll();
		
		return PatientDTOBuilder.fromListOfPatientToListOfRegPatientRespDto(allPatients);
		
	}
	
}