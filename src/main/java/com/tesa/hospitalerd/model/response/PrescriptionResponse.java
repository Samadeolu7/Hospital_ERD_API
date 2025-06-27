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
public class PrescriptionResponse {

    private Long prescriptionID;
    private Long patientID;
    private Long doctorID;
    private LocalDateTime prescriptionIssuedAt;
    private LocalDateTime prescriptionExpiryAt;
}
