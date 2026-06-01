package com.medsync.builder;

import java.util.List;

import com.medsync.dto.BedAssignmentHistoryDTO;
import com.medsync.model.BedAssignmentHistory;

public class BedAssignmentHistoryBuilder {
	
	public static List<BedAssignmentHistoryDTO> buildBedHistoryDTOFromBedHistory(List<BedAssignmentHistory> bedHistory) {
		
		return bedHistory.stream().map(history -> BedAssignmentHistoryDTO.builder()
				.BedAssignmentHistoryId(history.getBedAssignmentHistoryId())
				.bedNumber(history.getBed().getBedNumber())
				.assignedAt(history.getAssignedAt())
				.vacatedAt(history.getVacatedAt())
				.patientId(history.getPatientId()).build()).toList();
		
    }
}
