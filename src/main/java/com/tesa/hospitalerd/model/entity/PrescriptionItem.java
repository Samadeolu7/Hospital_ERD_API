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
public class PrescriptionItem {
    private Long prescriptionItemID;

    private Long prescriptionID;  // FK to Prescription

    private Long medicationID;    // FK to Medication

    private String prescriptionItemDosage;

    private Integer prescriptionItemQuantity;

    private String prescriptionItemInstructions;

    private String prescriptionItemStatus;

    private LocalDateTime prescriptionItemCreatedAt;

    private LocalDateTime prescriptionItemUpdatedAt;
}
