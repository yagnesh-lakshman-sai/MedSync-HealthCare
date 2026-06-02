package com.medsync.service;

import java.util.List;

import com.medsync.dto.RoomRequestDto;
import com.medsync.dto.RoomResponseDto;

public interface RoomService {

	RoomResponseDto UpdateRoomDetails(long roomNumber, RoomRequestDto roomRequestDto);

	public boolean removeRoom(long roomNumber);

	RoomResponseDto addRoom(RoomRequestDto roomrequestDto);
	List<RoomResponseDto> getAllRoomDetails();
}
