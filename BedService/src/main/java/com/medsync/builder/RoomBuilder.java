package com.medsync.builder;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

import com.medsync.dto.BedRequestDTO;
import com.medsync.dto.RoomRequestDto;
import com.medsync.model.Bed;
import com.medsync.model.Room;

public class RoomBuilder {
	
	public static Room buildRoomFromRoomDTO(RoomRequestDto roomRequestDto) {
		return Room.builder()
			        .roomNumber(roomRequestDto.getRoomNumber())
			        .roomType(roomRequestDto.getRoomType())
			        .roomCapacity(roomRequestDto.getRoomCapacity())
			        .beds(buildBedFromBedDTO(roomRequestDto.getBeds()))
			        .build();
	}
	
	private static List<Bed> buildBedFromBedDTO(List<BedRequestDTO> bedRequestDTOs){
		List<Bed> bedList = new ArrayList<>();
		if (bedRequestDTOs != null) { 
		for(BedRequestDTO beds : bedRequestDTOs) {
			Bed bed = new Bed();
			System.out.println(beds.getBedNumber());
			bed.setBedNumber(beds.getBedNumber());
            bed.setOccupied(beds.isOccupied());
			bedList.add(bed);
		}
		}
		return bedList;
	}
	
	
}

