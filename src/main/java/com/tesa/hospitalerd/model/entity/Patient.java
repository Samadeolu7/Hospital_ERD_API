package com.tesa.hospitalerd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private int patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientDOB;
    private String patientGender;
    private String patientBloodType;
    private String patientGenotype;
    private String patientAddress;
    private String patientPhoneNumber;
    private String patientEmail;
    private int patientAge;
    private PatientNextOfKin patientNextOfKin;
    private LocalDateTime patientCreatedAt;
    private LocalDateTime patientUpdatedAt;
}
