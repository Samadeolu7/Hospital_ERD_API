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
public class MedicationDispenseCreateRequest {
    private Long prescriptionItemID;
    private Long medicationID;
    private Long patientID;
    private Integer medicationDispenseQuantity;
    private Long medicationDispenseDispensedBy;
    private LocalDateTime medicationDispenseAt;
}

