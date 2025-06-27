package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationInventoryCreateRequest {
    private Long medicationID;
    private String medicationInventoryLocation;
    private Integer medicationInventoryTotalQuantity;
    private Integer medicationInventoryAvailableQuantity;
    private String medicationInventoryBatchNo;
    private LocalDate medicationInventoryExpiryDate;
}
