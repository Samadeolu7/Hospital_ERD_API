package com.tesa.hospitalerd.repository.database.query;

public class EquipmentInventoryQuery {
    public static final String CREATE_INVENTORY = """
        INSERT INTO TIS_EQUIPMENT_INVENTORY
          (equipmentID,
           equipmentTotalQuantity,
           equipmentAvailableQuantity,
           equipmentLocation,
           equipmentInventoryReorderLevel,
           equipmentInventoryStatus,
           equipmentInventoryCreatedAt,
           equipmentInventoryUpdatedAt)
        VALUES
          (:equipmentID,
           :totalQuantity,
           :availableQuantity,
           :location,
           :reorderLevel,
           COALESCE(:status,'ACTIVE'),
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP)
    """;

    public static final String UPDATE_INVENTORY = """
        UPDATE TIS_EQUIPMENT_INVENTORY
           SET equipmentID               = COALESCE(:equipmentID, equipmentID),
               equipmentTotalQuantity    = COALESCE(:totalQuantity, equipmentTotalQuantity),
               equipmentAvailableQuan    = COALESCE(:availableQuantity, equipmentAvailableQuantity),
               equipmentLocation         = COALESCE(:location, equipmentLocation),
               equipmentReorderLevel     = COALESCE(:reorderLevel, equipmentInventoryReorderLevel),
               equipmentInventoryStatus  = COALESCE(:status, equipmentInventoryStatus),
               equipmentInventoryUpdatedAt = CURRENT_TIMESTAMP
         WHERE equipmentInventoryID      = :id
    """;

    public static final String DELETE_INVENTORY = """
        UPDATE TIS_EQUIPMENT_INVENTORY
           SET equipmentInventoryStatus  = 'DELETED',
               equipmentInventoryUpdatedAt = CURRENT_TIMESTAMP
         WHERE equipmentInventoryID      = :id
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_EQUIPMENT_INVENTORY
         WHERE equipmentInventoryID = :id
           AND equipmentInventoryStatus != 'DELETED'
    """;

    public static final String FIND_ALL = """
        SELECT *
          FROM TIS_EQUIPMENT_INVENTORY
         WHERE equipmentInventoryStatus != 'DELETED'
    """;

    public static final String FIND_LOW_STOCK = """
        SELECT *
          FROM TIS_EQUIPMENT_INVENTORY
         WHERE equipmentAvailableQuantity < equipmentInventoryReorderLevel
           AND equipmentInventoryStatus != 'DELETED'
    """;
}