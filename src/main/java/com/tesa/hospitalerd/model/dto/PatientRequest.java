package com.tesa.hospitalerd.model.dto;

import com.tesa.hospitalerd.model.entity.PatientNextOfKin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {

    private String patientFirstName;
    private String patientLastName;
    private String patientDOB;
    private String patientGender;
    private String patientBloodType;
    private String patientGenotype;
    private String patientAddress;
    private String patientPhoneNumber;
    private String patientEmail;
    private int Age;
    private PatientNextOfKin patientNextOfKin;
}
