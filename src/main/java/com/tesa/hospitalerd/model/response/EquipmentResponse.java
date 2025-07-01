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
public class EquipmentResponse {
    private Long equipmentId;
    private String equipmentName;
    private String equipmentSerialNumber;
    private LocalDateTime equipmentPurchaseDate;
    private LocalDateTime equipmentLastMaintenanceDate;
    private String equipmentStatus;
    private LocalDateTime equipmentCreatedAt;
    private LocalDateTime equipmentUpdatedAt;
}
