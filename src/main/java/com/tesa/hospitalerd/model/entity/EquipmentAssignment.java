package com.tesa.hospitalerd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentAssignment {

    private Long equipmentAssignmentID;

    private Long equipmentInventoryID;  // FK to EquipmentInventory

    private Long equipmentAssignedTo;   // FK to Staff

    private LocalDateTime equipmentAssignedAt;

    private LocalDateTime equipmentReturnedAt;

    private Long equipmentReceivedBy;   // User who recorded return

    private Long equipmentGivenBy;      // User who recorded assign

    private String equipmentAssignmentStatus;

    private LocalDateTime equipmentAssignmentCreatedAt;

    private LocalDateTime equipmentAssignmentUpdatedAt;
}