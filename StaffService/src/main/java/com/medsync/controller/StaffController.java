package com.medsync.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.dto.ForgotPasswordRequestDto;
import com.medsync.dto.LoginRequest;
import com.medsync.dto.LoginResponse;
import com.medsync.dto.RegisterStaffDto;
import com.medsync.dto.ResetPasswordRequest;
import com.medsync.dto.StaffDetailsDto;
import com.medsync.dto.VerifyOtpRequest;
import com.medsync.model.Staff;
import com.medsync.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	private final StaffService staffService;

	public StaffController(StaffService staffService) {
		super();
		this.staffService = staffService;
	}
	
	@GetMapping()
	public ResponseEntity<List<StaffDetailsDto>> getAllStaffDetails(){
		List<StaffDetailsDto> allStaff = staffService.getAllStaff();
		return ResponseEntity.ok(allStaff);
		
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		LoginResponse response = staffService.login(request);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{staffId}")
	public Staff getStaffByStaffId(@PathVariable String staffId) {
		return staffService.getStaffByStaffId(staffId);
	}


	@GetMapping("/searchByStaffName")
	public ResponseEntity<List<StaffDetailsDto>> searchByStaffFirstNameOrLastName(
			@RequestParam(name = "name", required = true) String name) {
		return staffService.searchByStaffFirstNameOrLastName(name);
	}
	
	@GetMapping("/test-auth")
	public ResponseEntity<String> testAuth() {
		org.springframework.security.core.Authentication auth = 
			org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
		
		String authorities = auth.getAuthorities().stream()
			.map(a -> a.getAuthority())
			.collect(java.util.stream.Collectors.joining(", "));
		
		return ResponseEntity.ok("Authenticated as: " + auth.getName() + 
			" with authorities: " + authorities);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<StaffDetailsDto> registerStaffDetails(@RequestBody RegisterStaffDto registerStaffDto){
		StaffDetailsDto registeredStaffDetailsDto = staffService.registerStaffDeatils(registerStaffDto);	
		return  ResponseEntity.status(HttpStatus.CREATED).body(registeredStaffDetailsDto);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{staffId}")
	public ResponseEntity<StaffDetailsDto> updateStaff(@PathVariable String staffId,@RequestBody RegisterStaffDto staffDetailsDto) {
		StaffDetailsDto updatedStaff = staffService.updateStaff(staffId, staffDetailsDto);
		return ResponseEntity.ok(updatedStaff);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/resign/{staffId}")
	public ResponseEntity<String> deleteStaff(@PathVariable(name="staffId") String staffId) {
		
		 String deleteStaff = staffService.deleteStaff(staffId);
		 
		 return ResponseEntity.status(HttpStatus.OK).body(deleteStaff);

	}
	
	@GetMapping("/getDoctorName/{doctorId}")
	public String getDoctorName(@PathVariable(name="doctorId") String doctorId) {
		
		return staffService.getDoctorName(doctorId);
	}
	
	@GetMapping("/getSpecialization/{staffId}")
	public String getSpecialization(@PathVariable(name="staffId") String staffId) {
		return staffService.getSpecialization(staffId);
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDto request) {
		staffService.sendOtp(request.getEmail());
	    return ResponseEntity.ok("OTP sent to registered email, If exists");
	}
	
	@PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
            @RequestBody VerifyOtpRequest otpRequest) {

        staffService.verifyOtp(otpRequest);

        return ResponseEntity.ok("OTP verified successfully.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody ResetPasswordRequest request) {

        staffService.resetPassword(request);

        return ResponseEntity.ok("Password reset successfully.");
    }

}
