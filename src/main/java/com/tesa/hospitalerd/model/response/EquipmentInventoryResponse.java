
package com.tesa.hospitalerd.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentInventoryResponse {
    private Long equipmentInventoryID;
    private Long equipmentID;
    private Integer equipmentTotalQuantity;
    private Integer equipmentAvailableQuantity;
    private String equipmentLocation;
    private Integer equipmentReorderLevel;
}
