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
public class PatientNextOfKin {

    private int nextOfKinId;
    private String nextOfKinFirstName;
    private String nextOfKinLastName;
    private String nextOfKinPhoneNumber;
    private String nextOfKinEmail;
    private String nextOfKinAddress;
    private String nextOfKinGender;
    private LocalDateTime nextOfKinCreatedAt;
    private String nextOfKinCreatedBY;
    private LocalDateTime nextOfKinUpdatedAt;
    private String nextOfKinUpdatedBy;
}
