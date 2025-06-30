package com.tesa.hospitalerd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationInventory {
    private Long medicationInventoryID;

    private Long medicationID;  // FK to Medication

    private String medicationInventoryLocation;

    private String medicationInventoryAvailableQuantity;

    private Integer medicationInventoryTotalQuantity;

    private String medicationInventoryBatchNo;

    private LocalDate medicationInventoryExpiryDate;

    private String medicationInventoryStatus;

    private LocalDateTime medicationInventoryCreatedAt;

    private LocalDateTime medicationInventoryUpdatedAt;
}
