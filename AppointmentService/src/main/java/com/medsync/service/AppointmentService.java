package com.medsync.service;

import java.time.LocalDate;
import java.util.List;

import com.medsync.dto.AppointmentRequestDTO;
import com.medsync.dto.AppointmentResponseDTO;
import com.medsync.dto.RescheduleAppointmentDTO;
import com.medsync.model.Appointment;

public interface AppointmentService {

	List<String> getPatientsByDoctor(String staffId,LocalDate startDate, LocalDate endDate);

	AppointmentResponseDTO bookAppointment(AppointmentRequestDTO appointmentRequestDto);

	List<Appointment> getAllAppointmentsForAllDoctors(LocalDate date);
	
	List<Appointment> getAllAppointmentsOfDoctor(String doctorId,LocalDate date);
	
	List<Appointment> getAllFutureAppointmentsOfDoctor(String doctorId);

	AppointmentResponseDTO reScheduleAppointment(String appointmentId,RescheduleAppointmentDTO rescheduleAppointmentDTO);

	boolean cancelAppointment(String appointmentId);
	
	AppointmentResponseDTO getAppointmentDetails(String appointmentId);
}
