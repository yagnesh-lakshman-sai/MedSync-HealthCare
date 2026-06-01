package com.medsync.builder;

import com.medsync.dto.BedDetailsResponseDTO;
import com.medsync.model.Bed;

public class BedResponseDtoBuilder {
	
	public static BedDetailsResponseDTO buildBedDetailsResponseDtoFromBed(Bed bed) {
		return BedDetailsResponseDTO.builder()
								.bedNumber(bed.getBedNumber())
								.roomNumber(bed.getRoom().getRoomNumber())
								.isOccupied(bed.isOccupied())
								.build();
	}
}
