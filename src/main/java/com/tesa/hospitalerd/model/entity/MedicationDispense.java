package com.tesa.hospitalerd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDispense {
    private Long medicationDispenseID;

    private Long prescriptionItemID;  // FK to PrescriptionItem, nullable if OTC

    private Long medicationID;

    private Long patientID;

    private Integer medicationDispenseQuantity;

    private Long medicationDispenseDispensedBy;  // Staff/User

    private LocalDateTime medicationDispenseAt;

    private String medicationDispenseStatus;

    private LocalDateTime medicationDispenseCreatedAt;

    private LocalDateTime medicationDispenseUpdatedAt;
}
