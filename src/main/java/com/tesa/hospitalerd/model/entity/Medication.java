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
public class Medication {

    private Long medicationID;

    private String medicationName;

    private String medicationDescription;

    private Boolean medRequiresRX;

    private String medicationUnit;

    private Integer medicationAvailableQuantity;

    private Integer medicationTotalQuantity;

    private Integer medicationReorderLevel;

    private String medicationStatus;

    private LocalDateTime medicationCreatedAt;

    private LocalDateTime medicationUpdatedAt;
}