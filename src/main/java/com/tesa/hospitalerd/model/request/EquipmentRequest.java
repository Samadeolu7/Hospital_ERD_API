package com.tesa.hospitalerd.model.request;

import com.tesa.hospitalerd.model.entity.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequest {

    private String equipmentName;
    private String equipmentSerialNumber;
    private LocalDateTime equipmentPurchaseDate;
    private LocalDateTime equipmentLastMaintenanceDate;
    private EquipmentStatus equipmentStatus;
}
