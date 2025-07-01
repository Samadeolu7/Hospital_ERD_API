package com.tesa.hospitalerd.model.request;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentInventoryUpdateRequest {
    private Long equipmentInventoryID;
    private Long equipmentID;
    private Integer equipmentTotalQuantity;
    private Integer equipmentAvailableQuantity;
    private String equipmentInventoryLocation;
    private Integer equipmentInventoryReorderLevel;
}