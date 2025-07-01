package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionCreateRequest {
    private Long patientID;
    private Long doctorID;
    private LocalDateTime prescriptionIssuedAt;
    private LocalDateTime prescriptionExpiryAt;
}
