package com.medsync.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medsync.builder.BedBuilder;
import com.medsync.builder.BedResponseDtoBuilder;
import com.medsync.dao.BedRepository;
import com.medsync.dao.RoomRepository;
import com.medsync.dto.BedDetailsResponseDTO;
import com.medsync.dto.BedRequestDTO;
import com.medsync.dto.RoomResponseDto;
import com.medsync.exception.BedNotFoundException;
import com.medsync.exception.BedUnavailableException;
import com.medsync.exception.RoomNotFoundException;
import com.medsync.model.Bed;
import com.medsync.model.Room;
import com.medsync.service.BedService;

@Service
public class BedServiceImpl implements BedService {

	private final BedRepository bedRepository;
	private final RoomRepository roomRepository;

	public BedServiceImpl(BedRepository bedRepository, RoomRepository roomRepository) {

		this.bedRepository = bedRepository;
		this.roomRepository = roomRepository;
	}

	@Override
	public ResponseEntity<String> addBedInRoom(BedRequestDTO bedRequestDTO) {

		Room existingRoom = roomRepository.findByRoomNumber(bedRequestDTO.getRoomNumber());
		if (existingRoom == null) {
			return ResponseEntity.ok("Room Number " + bedRequestDTO.getRoomNumber() + " doesn't exists");
		}
		List<Bed> bedInExistingRoom = existingRoom.getBeds();
		for (Bed bed : bedInExistingRoom) {
			if (bedRequestDTO.getBedNumber() == bed.getBedNumber()) {
				return ResponseEntity.ok("Bed Number " + bedRequestDTO.getBedNumber() + " already added in Room Number "
						+ bedRequestDTO.getRoomNumber());
			}

		}
		if (bedInExistingRoom.isEmpty() || (bedInExistingRoom.size() < existingRoom.getRoomCapacity())) {
			Bed bed = BedBuilder.buildBedFromBedRequestDto(bedRequestDTO);
			bed.setRoom(existingRoom);
			BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(bedRepository.save(bed));
			return ResponseEntity.ok("Sucessfully added Bed Number " + bedRequestDTO.getBedNumber() + " into Room Number "
					+ bedRequestDTO.getRoomNumber());

		}

		return ResponseEntity.ok("Cannot add Bed into Room Number " + bedRequestDTO.getRoomNumber()
				+ " as the room is fulled with beds");
	}

	@Override
	public ResponseEntity<String> removeBed(long bedNumber, long roomNumber) {
		Bed bed = bedRepository.findBedWithRoomNumber(bedNumber, roomNumber).orElseThrow(()-> new BedNotFoundException("Bed Not Found with BedNumber :"+bedNumber+" in RoomNumber: "+roomNumber));

		bedRepository.delete(bed);
		return ResponseEntity.ok("Bed removed from the room");
	}


	@Override
	public ResponseEntity<BedDetailsResponseDTO> updateBedDetails(long roomNumber, long bedNumber,
			BedRequestDTO bedRequestDTO) {
		Room existingRoom = roomRepository.findById(roomNumber).orElseThrow(()->new RoomNotFoundException("Room not Found with Id:"+roomNumber));
		Bed existingBed = bedRepository.findBedWithRoomNumber(bedNumber, existingRoom.getRoomNumber())
				 										.orElseThrow(()-> new BedNotFoundException("Bed Not Found with BedNumber :"+bedNumber+" in RoomNumber: "+roomNumber));
		existingBed.setOccupied(bedRequestDTO.isOccupied());
		existingBed.setRoom(existingRoom);
		Bed savedBed = bedRepository.save(existingBed);
		BedDetailsResponseDTO response = BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(savedBed);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public List<BedDetailsResponseDTO> getBedsByRoomId(long roomNumber) {

		Room room = roomRepository.findById(roomNumber)
	            .orElseThrow(() -> 
	                new RoomNotFoundException("Room not found with room number " + roomNumber));

	    List<Bed> beds = bedRepository.findByRoomRoomNumber(roomNumber);

	    if (beds.isEmpty()) {
	        throw new BedNotFoundException("No beds found for room number " + roomNumber);
	    }

	    return beds.stream()
	            .map(BedResponseDtoBuilder::buildBedDetailsResponseDtoFromBed)
	            .toList();
	}
	
	@Override
	public List<BedDetailsResponseDTO> getVacantBedsByRoomNumber(long roomNumber) {

		Room room = roomRepository.findById(roomNumber)
	            .orElseThrow(() ->
	                new RoomNotFoundException("Room not found with room number " + roomNumber));

	    List<Bed> vacantBeds = bedRepository.findByRoomRoomNumberAndIsOccupiedFalse(roomNumber);

	    if (vacantBeds.isEmpty()) {
	        throw new BedUnavailableException("No vacant beds available in room number " + roomNumber);
	    }

	    return vacantBeds.stream()
	            .map(BedResponseDtoBuilder::buildBedDetailsResponseDtoFromBed)
	            .toList();
	}

	@Override
	public List<BedDetailsResponseDTO> getAllBedDetails() {
		List<Bed> allBeds = bedRepository.findAll();
		List<BedDetailsResponseDTO> allBedsResponse = allBeds.stream().map(bed ->BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(bed)).toList();
		return allBedsResponse;
	}

}
