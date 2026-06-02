package com.medsync.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.medsync.builder.BedBuilder;
import com.medsync.builder.RoomBuilder;
import com.medsync.builder.RoomDTOBuilder;
import com.medsync.controller.RoomController;
import com.medsync.dao.RoomRepository;
import com.medsync.dto.BedRequestDTO;
import com.medsync.dto.RoomRequestDto;
import com.medsync.dto.RoomResponseDto;
import com.medsync.exception.RoomNotFoundException;
import com.medsync.model.Bed;
import com.medsync.model.Room;
import com.medsync.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {


	private final RoomRepository roomRepository;
	

	public RoomServiceImpl(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}
	


	@Override
	public RoomResponseDto addRoom(RoomRequestDto roomRequestDto) {
		
		System.out.println("RoomRequestDto "+roomRequestDto);

	    Room room = RoomBuilder.buildRoomFromRoomDTO(roomRequestDto);

	    if (roomRequestDto.getBeds() != null) {
	        List<Bed> bedEntities = new ArrayList<>();
	        for (BedRequestDTO bedDto : roomRequestDto.getBeds()) {
	           
	            Bed bedEntity = BedBuilder.buildBedFromBedRequestDto(bedDto);
	            bedEntities.add(bedEntity);
	            bedEntity.setRoom(room);
	            
	        }
	        room.setBeds(bedEntities);
	    }
	   System.out.println("Room "+room.getBeds());
	    Room savedRoom = roomRepository.save(room);
	  	    
	    return RoomDTOBuilder.buildRoomResponseDtofromRoom(savedRoom);
	}

	


	@Override
	public RoomResponseDto UpdateRoomDetails(long roomNumber,  RoomRequestDto roomRequestDto ) {
		Room existingRoom = roomRepository.findById(roomNumber)
											.orElseThrow(()-> new RoomNotFoundException("Room Not Found with Room Number :"+roomNumber));
	
//		Room updatedRoom = RoomBuilder.buildRoomFromRoomDTO(roomRequestDto);
//		updatedRoom.setRoomNumber(existingRoom.getRoomNumber());
//		
//		Room room = roomRepository.save(updatedRoom);
		existingRoom.setRoomType(roomRequestDto.getRoomType());
		existingRoom.setRoomCapacity(roomRequestDto.getRoomCapacity());

//		// Clear old beds
//		existingRoom.getBeds().clear();
//
//		for (BedRequestDTO dto : roomRequestDto.getBeds()) {
//		    Bed bed = new Bed();
//		    bed.setBedNumber(dto.getBedNumber());
//		    bed.setOccupied(dto.isOccupied());
//		    bed.setRoom(existingRoom);
//		    existingRoom.getBeds().add(bed);
//		}

		Room room = roomRepository.save(existingRoom);
		return RoomDTOBuilder.buildRoomResponseDtofromRoom(room);
		
	}
	
	@Override
	public boolean removeRoom(long roomNumber) {
		Room room = roomRepository.findById(roomNumber)
				.orElseThrow(() -> new RoomNotFoundException("Room not found with " + roomNumber));
		roomRepository.delete(room);
		return true;
	}
	@Override
	public List<RoomResponseDto> getAllRoomDetails() {
		 List<Room> rooms = roomRepository.findAll();
		 List<RoomResponseDto> roomResponse=new ArrayList<>();
		 for(Room room:rooms) {
			 RoomResponseDto roomResponseDtofromRoom = RoomDTOBuilder.buildRoomResponseDtofromRoom(room);
			 roomResponse.add(roomResponseDtofromRoom);
		 }
		return roomResponse;
	}


}
