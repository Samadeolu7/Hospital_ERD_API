package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentInventoryCreateRequest {
    private Long equipmentID;
    private Integer equipmentTotalQuantity;
    private Integer equipmentAvailableQuantity;
    private String equipmentLocation;
    private Integer equipmentReorderLevel;
}