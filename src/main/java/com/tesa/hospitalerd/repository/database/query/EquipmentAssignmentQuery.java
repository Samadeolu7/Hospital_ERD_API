package com.tesa.hospitalerd.repository.database.query;

public class EquipmentAssignmentQuery {
    public static final String CREATE_ASSIGNMENT = """
        INSERT INTO TIS_EQUIPMENT_ASSIGNMENT
          (equipmentInventoryID,
           equipmentAssignedAt,
           equipmentAssignedTo,
           equipmentReturnedAt,
           equipmentReceivedBy,
           equipmentGivenBy,
           equipmentAssignmentStatus,
           equipmentAssignmentCreatedAt,
           equipmentAssignmentUpdatedAt)
        VALUES
          (:equipmentInventoryID,
           :equipmentAssignedAt,
           :equipmentAssignedTo,
           :equipmentReturnedAt,
           :equipmentReceivedBy,
           :equipmentGivenBy,
           COALESCE(:equipmentAssignmentStatus,'ACTIVE'),
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP)
    """;

    public static final String UPDATE_ASSIGNMENT = """
        UPDATE TIS_EQUIPMENT_ASSIGNMENT
           SET equipmentInventoryID        = COALESCE(:equipmentInventoryID, equipmentInventoryID),
               equipmentAssignedAt        = COALESCE(:equipmentAssignedAt, equipmentAssignedAt),
               equipmentAssignedTo        = COALESCE(:equipmentAssignedTo, equipmentAssignedTo),
               equipmentReturnedAt        = COALESCE(:equipmentReturnedAt, equipmentReturnedAt),
               equipmentReceivedBy        = COALESCE(:equipmentReceivedBy, equipmentReceivedBy),
               equipmentGivenBy           = COALESCE(:equipmentGivenBy, equipmentGivenBy),
               equipmentAssignmentStatus  = COALESCE(:equipmentAssignmentStatus, equipmentAssignmentStatus),
               equipmentAssignmentUpdatedAt = CURRENT_TIMESTAMP
         WHERE equipmentAssignmentID     = :id
    """;

    public static final String DELETE_ASSIGNMENT = """
        UPDATE TIS_EQUIPMENT_ASSIGNMENT
           SET equipmentAssignmentStatus  = 'DELETED',
               equipmentAssignmentUpdatedAt = CURRENT_TIMESTAMP
         WHERE equipmentAssignmentID     = :id
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_EQUIPMENT_ASSIGNMENT
         WHERE equipmentAssignmentID = :id
           AND equipmentAssignmentStatus != 'DELETED'
    """;

    public static final String FIND_ALL = """
        SELECT *
          FROM TIS_EQUIPMENT_ASSIGNMENT
         WHERE equipmentAssignmentStatus != 'DELETED'
    """;

    public static final String FIND_BY_STAFF_ID = """
        SELECT *
          FROM TIS_EQUIPMENT_ASSIGNMENT
         WHERE equipmentAssignedTo = :staffId
           AND equipmentAssignmentStatus != 'DELETED'
    """;

    public static final String FIND_UNRETURNED = """
        SELECT *
          FROM TIS_EQUIPMENT_ASSIGNMENT
         WHERE equipmentReturnedAt IS NULL
           AND equipmentAssignmentStatus != 'DELETED'
    """;
}
