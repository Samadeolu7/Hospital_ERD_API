package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationUpdateRequest {
    private String medicationName;
    private String medicationDescription;
    private Boolean medicationRequiresRX;
    private String medicationUnit;
    private Integer medicationReorderLevel;
}
