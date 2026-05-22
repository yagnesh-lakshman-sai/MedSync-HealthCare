package com.medsync.dto;

import java.time.LocalDate;

import com.medsync.enums.Specialization;
import com.medsync.enums.StaffType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {

    private String to;
    private String subject;
    private String body;

    
}