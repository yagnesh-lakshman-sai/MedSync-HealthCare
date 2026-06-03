package com.medsync.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medsync.builder.BedAssignmentHistoryBuilder;
import com.medsync.dao.BedAssignmentHistoryRepository;
import com.medsync.dao.BedRepository;
import com.medsync.dto.BedAssignmentHistoryDTO;
import com.medsync.dto.BedDetailsResponseDTO;
import com.medsync.exception.BedNotFoundException;
import com.medsync.exception.BedUnavailableException;
import com.medsync.model.Bed;
import com.medsync.model.BedAssignmentHistory;
import com.medsync.service.BedAssignmentService;

@Service
public class BedAssignmentServiceImpl implements BedAssignmentService {

	private final BedRepository bedRepository;
	private final BedAssignmentHistoryRepository bedHistoryRepository;

	public BedAssignmentServiceImpl(BedRepository bedRepository, BedAssignmentHistoryRepository bedHistoryRepository) {
		this.bedRepository = bedRepository;
		this.bedHistoryRepository = bedHistoryRepository;
	}

	@Override
	public Bed bedAssigntment(long bedNumber, long patientId) {

		Bed bed = bedRepository.findById(bedNumber).orElseThrow(() -> new BedNotFoundException("Bed not found"));

		if (bed.isOccupied()) {
			throw new BedUnavailableException("Bed " + bedNumber + " is already occupied");
		}

		bed.setOccupied(true);
		bed.setPatientId(patientId);
		bedRepository.save(bed);

		BedAssignmentHistory bedHistory = new BedAssignmentHistory();
		bedHistory.setBed(bed);
		bedHistory.setPatientId(patientId);
		bedHistory.setAssignedAt(LocalDateTime.now());

		bedHistoryRepository.save(bedHistory);

		return bed;
	}

	@Override
	public void vacateBed(long roomNumber, long bedNumber) {
		Bed bed = bedRepository.findBedWithRoomNumber(bedNumber, roomNumber).orElseThrow(()-> new BedNotFoundException("Bed Not Found with BedNumber :"+bedNumber+" in RoomNumber: "+roomNumber));
		BedAssignmentHistory activeAssignment = bedHistoryRepository.findTopByBed_BedNumberAndVacatedAtIsNullOrderByAssignedAtDesc(bedNumber);

	    activeAssignment.setVacatedAt(LocalDateTime.now());
	    
		bed.setOccupied(false);
	    bed.setPatientId(0);
	    bedRepository.save(bed);
	    bedHistoryRepository.save(activeAssignment);
	}

	@Override
	public List<BedAssignmentHistoryDTO> getHistoryByBedNumber(long bedNumber) {
		List<BedAssignmentHistory> bedHistory = bedHistoryRepository.findByBed_BedNumber(bedNumber);
		List<BedAssignmentHistoryDTO> bedHistoryDTO = BedAssignmentHistoryBuilder.buildBedHistoryDTOFromBedHistory(bedHistory);
		return bedHistoryDTO;
	}

}
