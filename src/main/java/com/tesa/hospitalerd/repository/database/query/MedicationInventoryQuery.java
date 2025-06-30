package com.tesa.hospitalerd.repository.database.query;

public class MedicationInventoryQuery {
    public static final String CREATE_INVENTORY = """
        INSERT INTO TIS_MEDICATION_INVENTORY
          (medicationID,
           medicationInventoryLocation,
           medicationInventorytotalQuantity,
           medicationInventoryAvailableQuantity,
           medicationInventoryBatchNo,
           medicationInventoryExpiryDate,
           medicationInventoryStatus,
           medicationInventoryCreatedAt,
           medicationInventoryUpdatedAt)
        VALUES
          (:medicationID,
           :location,
           :totalQuantity,
           :availableQuantity,
           :batchNumber,
           :expiryDate,
           COALESCE(:status,'ACTIVE'),
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP)
    """;

    public static final String UPDATE_INVENTORY = """
        UPDATE TIS_MEDICATION_INVENTORY
           SET medicationID                     = COALESCE(:medicationID, medicationID),
               medicationInventoryLocation                 = COALESCE(:location, medicationInventoryLocation),
               medicationInventorytotalQuantity            = COALESCE(:totalQuantity, medicationInventorytotalQuantity),
               medicationInventoryAvailableQuantity            = COALESCE(:availableQuantity, medicationInventoryAvailableQuantity),
               medicationInventoryBatchNo                  = COALESCE(:batchNumber, medicationInventoryBatchNo),
               medicationInventoryExpiryDate               = COALESCE(:expiryDate, medicationInventoryExpiryDate),
               medicationInventoryStatus        = COALESCE(:status, medicationInventoryStatus),
               medicationInventoryUpdatedAt     = CURRENT_TIMESTAMP
         WHERE medicationInventoryID               = :id
    """;

    public static final String DELETE_INVENTORY = """
        UPDATE TIS_MEDICATION_INVENTORY
           SET medicationInventoryStatus        = 'DELETED',
               medicationInventoryUpdatedAt     = CURRENT_TIMESTAMP
         WHERE medicationInventoryID               = :id
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationInventoryID = :id
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String FIND_ALL = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationInventoryStatus != 'DELETED'
    """;

    public static final String FIND_BY_MEDICATION = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationID = :medicationId
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String FIND_EXPIRED = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationInventoryExpiryDate < :date
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String FIND_LOW_STOCK = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationInventoryAvailableQuantity < medicationInventoryReorderLevel
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String SUM_AVAILABLE_BY_MEDICATION_ID = """
        SELECT COALESCE(SUM(medicationInventoryAvailableQuantity),0)
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationID = :medicationId
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String SUM_TOTAL_BY_MEDICATION_ID = """
        SELECT COALESCE(SUM(medicationInventoryTotalQuantity),0)
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationID = :medicationId
           AND medicationInventoryStatus != 'DELETED'
    """;
}
