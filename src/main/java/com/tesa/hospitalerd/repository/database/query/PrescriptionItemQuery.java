package com.tesa.hospitalerd.repository.database.query;

public class PrescriptionItemQuery {
    public static final String CREATE_ITEM = """
        INSERT INTO TIS_PRESCRIPTION_ITEM
          (prescriptionID,
           medicationID,
           presItemDosage,
           presItemQuantity,
           presItemInstructions,
           prescriptionItemStatus,
           prescriptionItemCreatedAt,
           prescriptionItemUpdatedAt)
        VALUES
          (:prescriptionID,
           :medicationID,
           :dosage,
           :quantity,
           :instructions,
           COALESCE(:status,'ACTIVE'),
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP)
    """;

    public static final String UPDATE_ITEM = """
        UPDATE TIS_PRESCRIPTION_ITEM
           SET prescriptionID          = COALESCE(:prescriptionID, prescriptionID),
               medicationID            = COALESCE(:medicationID, medicationID),
               presItemDosage          = COALESCE(:dosage, presItemDosage),
               presItemQuantity        = COALESCE(:quantity, presItemQuantity),
               presItemInstructions    = COALESCE(:instructions, presItemInstructions),
               prescriptionItemStatus  = COALESCE(:status, prescriptionItemStatus),
               prescriptionItemUpdatedAt = CURRENT_TIMESTAMP
         WHERE prescriptionItemID     = :id
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_PRESCRIPTION_ITEM
         WHERE prescriptionItemID = :id
           AND prescriptionItemStatus != 'DELETED'
    """;

    public static final String COUNT_BY_PRESCRIPTION = """
        SELECT COUNT(*)
          FROM TIS_PRESCRIPTION_ITEM
         WHERE prescriptionID = :prescriptionId
           AND prescriptionItemStatus != 'DELETED'
    """;

    public static final String DELETE_BY_PRESCRIPTION = """
        UPDATE TIS_PRESCRIPTION_ITEM
           SET prescriptionItemStatus = 'DELETED',
               prescriptionItemUpdatedAt = CURRENT_TIMESTAMP
         WHERE prescriptionID = :prescriptionId
    """;

    public static final String FIND_BY_PRESCRIPTION = """
        SELECT *
          FROM TIS_PRESCRIPTION_ITEM
         WHERE prescriptionID = :prescriptionId
           AND prescriptionItemStatus != 'DELETED'
    """;
}
