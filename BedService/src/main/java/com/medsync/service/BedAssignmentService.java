package com.medsync.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.medsync.dto.BedAssignmentHistoryDTO;
import com.medsync.dto.BedDetailsResponseDTO;
import com.medsync.model.Bed;

public interface BedAssignmentService {

	Bed bedAssigntment(long bedNumber, long patientId);

	void vacateBed(long roomNumber, long bedNumber);

	List<BedAssignmentHistoryDTO> getHistoryByBedNumber(long bedNumber);
}
