package com.medsync.builder;


import java.util.ArrayList;
import java.util.List;

import com.medsync.dto.BedDetailsResponseDTO;
import com.medsync.dto.RoomResponseDto;
import com.medsync.model.Bed;
import com.medsync.model.Room;

public class RoomDTOBuilder {
	
	public static RoomResponseDto buildRoomResponseDtofromRoom(Room room) {
		
		return RoomResponseDto
				.builder()
				.roomNumber(room.getRoomNumber())
				.roomType(room.getRoomType())
				.roomCapacity(room.getRoomCapacity())
				.beds(buildBedDetailsResponseDtos(room.getBeds()))
				.build();
	}

	private static List<BedDetailsResponseDTO> buildBedDetailsResponseDtos(List<Bed> beds) {

		List<BedDetailsResponseDTO> bedDetailsResponseDto = new ArrayList<>();
		
		for (Bed bed : beds) {
			bedDetailsResponseDto.add(BedResponseDtoBuilder.buildBedDetailsResponseDtoFromBed(bed));
		}
		
		return bedDetailsResponseDto;
	}
}
