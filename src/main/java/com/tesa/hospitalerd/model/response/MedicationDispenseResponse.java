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
public class MedicationDispenseResponse {
    private Long medicationDispenseID;
    private Long prescriptionItemID;
    private Long medicationID;
    private Long patientID;
    private Integer medicationDispenseQuantity;
    private Long medicationDispenseDispensedBy;
    private Integer medicationDispenseSalePrice;
    private String medicationDispenseStatus;
    private LocalDateTime medicationDispenseDispensedAt;
    private LocalDateTime medicationDispenseCreatedAt;
    private LocalDateTime medicationDispenseUpdatedAt;
}
