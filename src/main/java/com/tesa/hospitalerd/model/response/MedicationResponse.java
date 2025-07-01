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
public class MedicationResponse {
    private Long medicationID;
    private String medicationName;
    private String medicationDescription;
    private Boolean medRequiresRX;
    private String medicationUnit;
    private Integer medicationReorderLevel;
    private Integer medicationAvailableQuantity;
    private Integer medicationTotalQuantity;
    private String medicationStatus;
    private LocalDateTime medicationCreatedAt;
    private LocalDateTime medicationUpdatedAt;
}
