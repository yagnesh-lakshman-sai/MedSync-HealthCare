package com.medsync.builder;

import com.medsync.dto.BedRequestDTO;
import com.medsync.model.Bed;

public class BedBuilder {

	public static Bed buildBedFromBedRequestDto(BedRequestDTO bedRequestDTO) {
		return Bed.builder()
				  .bedNumber(bedRequestDTO.getBedNumber())
				  .isOccupied(bedRequestDTO.isOccupied())
				  .build();
	}
}
