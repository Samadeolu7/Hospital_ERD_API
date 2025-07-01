package com.tesa.hospitalerd.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponse {
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientDOB;
    private String patientGender;
    private String patientBloodType;
    private String patientGenotype;
    private String patientAddress;
    private String patientPhoneNumber;
    private String patientEmail;
    private Integer patientAge;
    private LocalDateTime patientCreatedAt;
    private LocalDateTime patientUpdatedAt;
}
