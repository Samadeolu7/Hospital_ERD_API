package com.tesa.hospitalerd.model.dto;

import com.tesa.hospitalerd.model.entity.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequest {

    private String equipmentName;
    private String equipmentSerialNumber;
    private LocalDate equipmentPurchaseDate;
    private LocalDate equipmentLastMaintenanceDate;
    private EquipmentStatus equipmentStatus;
}
