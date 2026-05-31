package com.medsync.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.medsync.dto.BedDetailsResponseDTO;
import com.medsync.dto.BedRequestDTO;
import com.medsync.dto.RoomResponseDto;

public interface BedService {

	ResponseEntity<String> removeBed(long bedNumber, long roomNumber);

	ResponseEntity<String> addBedInRoom(BedRequestDTO bedRequestDTO);

	ResponseEntity<BedDetailsResponseDTO> updateBedDetails(long roomNumber, long bedNumber, BedRequestDTO bedRequestDTO);
	
	List<BedDetailsResponseDTO> getBedsByRoomId(long roomNumber);
	
	List<BedDetailsResponseDTO> getVacantBedsByRoomNumber(long roomNumber);

	List<BedDetailsResponseDTO> getAllBedDetails();

}
