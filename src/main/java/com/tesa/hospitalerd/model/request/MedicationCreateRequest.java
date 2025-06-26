package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationCreateRequest {
    private String medicationName;
    private String medicationDescription;
    private Boolean medRequiresRX;
    private String medicationUnit;
    private Integer medicationReorderLevel;
}
