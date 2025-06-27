package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemUpdateRequest {
    private String prescriptionItemID;
    private Long prescriptionID;
    private Long medicationID;
    private String prescriptionItemDosage;
    private Integer prescriptionItemQuantity;
    private String prescriptionItemInstructions;
}
