package com.tesa.hospitalerd.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationInventoryResponse {
    private Long medicationInventoryID;
    private Long medicationID;
    private String medInvenLocation;
    private Integer medInvenTotalQuantity;
    private Integer medInvenAvailableQuantity;
    private String medInvenBatchNo;
    private LocalDate medInvenExpiryDate;
}
