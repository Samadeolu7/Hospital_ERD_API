package com.tesa.hospitalerd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    private int equipmentId;
    private String equipmentName;
    private String equipmentSerialNumber;
    private LocalDate equipmentPurchaseDate;
    private LocalDate equipmentLastMaintenanceDate;
    private EquipmentStatus equipmentStatus;
    private LocalDateTime equipmentCreatedAt;
    private LocalDateTime equipmentUpdated;
}
