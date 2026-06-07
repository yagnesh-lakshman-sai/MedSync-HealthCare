package com.medsync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.dto.BedAssignmentHistoryDTO;
import com.medsync.dto.BedDetailsResponseDTO;
import com.medsync.model.Bed;
import com.medsync.service.BedAssignmentService;

@RestController
@RequestMapping("/bed")
public class BedAssignmentController {

	private final BedAssignmentService bedAssignmentService;

	public BedAssignmentController(BedAssignmentService bedAssignmentService) {
		this.bedAssignmentService = bedAssignmentService;
	}

	@PostMapping("/assign/{bedNumber}/{patientId}")
	public ResponseEntity<Bed> assignBed(@PathVariable(name = "bedNumber") long bedNumber,
			@PathVariable(name = "patientId") long patientId) {

		Bed assignedBed = bedAssignmentService.bedAssigntment(bedNumber, patientId);
		return ResponseEntity.ok(assignedBed);
	}
	
	@PutMapping("/vacate-bed/{roomNumber}/{bedNumber}")
	public ResponseEntity<String> vacateBed(
	        @PathVariable long roomNumber,
	        @PathVariable long bedNumber) {
		
		bedAssignmentService.vacateBed(roomNumber, bedNumber);
	    return ResponseEntity.ok().build();
	}
	
	@GetMapping("/bed-history/{bedNumber}")
    public ResponseEntity<List<BedAssignmentHistoryDTO>> 
        getHistoryByBed(@PathVariable long bedNumber) {
		List<BedAssignmentHistoryDTO> historyByBedNumber = bedAssignmentService.getHistoryByBedNumber(bedNumber);
        
		return ResponseEntity.ok(historyByBedNumber);
    }
}
