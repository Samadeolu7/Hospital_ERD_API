package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemCreateRequest {
    private Long prescriptionID;
    private Long medicationID;
    private String presItemDosage;
    private Integer presItemQuantity;
    private String presItemInstructions;
}
