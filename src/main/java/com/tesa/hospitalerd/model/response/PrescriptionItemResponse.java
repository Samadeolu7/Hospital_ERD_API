package com.tesa.hospitalerd.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemResponse {
    private Long prescriptionItemID;
    private Long prescriptionID;
    private Long medicationID;
    private String presItemDosage;
    private Integer presItemQuantity;
    private String presItemInstructions;
}
