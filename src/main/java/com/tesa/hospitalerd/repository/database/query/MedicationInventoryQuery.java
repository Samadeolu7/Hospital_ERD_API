package com.tesa.hospitalerd.repository.database.query;

public class MedicationInventoryQuery {
    public static final String CREATE_INVENTORY = """
        INSERT INTO TIS_MEDICATION_INVENTORY
          (medicationID,
           medInvenLocation,
           medInventotalQuantity,
           medInvenAvailableQuan,
           medInvenBatchNo,
           medInvenExpiryDate,
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
               medInvenLocation                 = COALESCE(:location, medInvenLocation),
               medInventotalQuantity            = COALESCE(:totalQuantity, medInventotalQuantity),
               medInvenAvailableQuan            = COALESCE(:availableQuantity, medInvenAvailableQuan),
               medInvenBatchNo                  = COALESCE(:batchNumber, medInvenBatchNo),
               medInvenExpiryDate               = COALESCE(:expiryDate, medInvenExpiryDate),
               medicationInventoryStatus        = COALESCE(:status, medicationInventoryStatus),
               medicationInventoryUpdatedAt     = CURRENT_TIMESTAMP
         WHERE medicationInvenID               = :id
    """;

    public static final String DELETE_INVENTORY = """
        UPDATE TIS_MEDICATION_INVENTORY
           SET medicationInventoryStatus        = 'DELETED',
               medicationInventoryUpdatedAt     = CURRENT_TIMESTAMP
         WHERE medicationInvenID               = :id
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationInvenID = :id
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String FIND_BY_MEDICATION = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medicationID = :medId
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String FIND_EXPIRED = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medInvenExpiryDate < :date
           AND medicationInventoryStatus != 'DELETED'
    """;

    public static final String FIND_LOW_STOCK = """
        SELECT *
          FROM TIS_MEDICATION_INVENTORY
         WHERE medInvenAvailableQuan < medicationReorderLevel
           AND medicationInventoryStatus != 'DELETED'
    """;
}
