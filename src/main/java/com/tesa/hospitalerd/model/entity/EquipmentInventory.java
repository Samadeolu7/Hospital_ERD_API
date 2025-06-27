package com.tesa.hospitalerd.model.entity;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name = "equipment_inventory")
public class EquipmentInventory {

    private Long equipmentInventoryID;

    private Long equipmentID;  // FK to Equipment

    private Integer equipmentTotalQuantity;

    private Integer equipmentAvailableQuantity;

    private String equipmentInventoryLocation;

    private Integer equipmentInventoryReorderLevel;

    private String equipmentInventoryStatus;

    private LocalDateTime equipmentInventoryCreatedAt;

    private LocalDateTime equipmentInventoryUpdatedAt;
}