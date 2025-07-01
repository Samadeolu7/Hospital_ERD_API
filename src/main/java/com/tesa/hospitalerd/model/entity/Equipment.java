package com.tesa.hospitalerd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    private Long equipmentId;
    private String equipmentName;
    private String equipmentSerialNumber;
    private LocalDateTime equipmentPurchaseDate;
    private LocalDateTime equipmentLastMaintenanceDate;
    private EquipmentStatus equipmentStatus;
    private LocalDateTime equipmentCreatedAt;
    private LocalDateTime equipmentUpdatedAt;
}
