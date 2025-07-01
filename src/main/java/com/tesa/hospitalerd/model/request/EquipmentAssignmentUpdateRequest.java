package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentAssignmentUpdateRequest {
    private Long equipmentAssignmentID;
    private Long equipmentInventoryID;
    private Long equipmentAssignedTo;
    private LocalDateTime equipmentAssignedAt;
    private Long equipmentReceivedBy;
    private Long equipmentGivenBy;
}
