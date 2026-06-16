package com.medsync.builder;


import com.medsync.dto.AppointmentRequestDTO;
import com.medsync.model.Appointment;

public class AppointmentBuilder {
	
	public static Appointment buildAppointmentFromAppointmentRequestDTO(AppointmentRequestDTO appointmentRequestDTO){
		
		return Appointment.builder()
					.patientId(appointmentRequestDTO.getPatientId())
					.doctorId(appointmentRequestDTO.getDoctorId())
					.appointmentDate(appointmentRequestDTO.getAppointmentDate())
					.startTime(appointmentRequestDTO.getStartTime())
					.endTime(appointmentRequestDTO.getEndTime())
					.notes(appointmentRequestDTO.getNotes())
					.build();
		
		
		
	}

}
