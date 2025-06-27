package com.tesa.hospitalerd.repository.database.query;

public class MedicationDispenseQuery {
    public static final String CREATE_DISPENSE = """
        INSERT INTO TIS_MEDICATION_DISPENSE
          (prescriptionItemID,
           medicationID,
           patientID,
           medicationDispenseQuantity,
           medicationDispenseDispensedBy,
           medicationDispenseDispensedAt,
           medicationDispenseSalePrice,
           medicationDispenseStatus,
           medicationDispenseCreatedAt,
           medicationDispenseUpdatedAt)
        VALUES
          (:prescriptionItemID,
           :medicationID,
           :patientID,
           :quantity,
           :dispensedBy,
           :dispensedAt,
           :salePrice,
           COALESCE(:status,'ACTIVE'),
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP)
    """;

    public static final String UPDATE_DISPENSE = """
        UPDATE TIS_MEDICATION_DISPENSE
           SET prescriptionItemID             = COALESCE(:prescriptionItemID, prescriptionItemID),
               medicationID                   = COALESCE(:medicationID, medicationID),
               patientID                      = COALESCE(:patientID, patientID),
               medicationDispenseQuantity     = COALESCE(:quantity, medicationDispenseQuantity),
               medicationDispenseDispensedBy  = COALESCE(:dispensedBy, medicationDispenseDispensedBy),
               medicationDispenseDispensedAt  = COALESCE(:dispensedAt, medicationDispenseDispensedAt),
               medicationDispenseSalePrice    = COALESCE(:salePrice, medicationDispenseSalePrice),
               medicationDispenseStatus       = COALESCE(:status, medicationDispenseStatus),
               medicationDispenseUpdatedAt    = CURRENT_TIMESTAMP
         WHERE medicationDispenseID          = :id
    """;

    public static final String DELETE_DISPENSE = """
        UPDATE TIS_MEDICATION_DISPENSE
           SET medicationDispenseStatus     = 'DELETED',
               medicationDispenseUpdatedAt  = CURRENT_TIMESTAMP
         WHERE medicationDispenseID         = :id
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_MEDICATION_DISPENSE
         WHERE medicationDispenseID = :id
           AND medicationDispenseStatus != 'DELETED'
    """;

    public static final String FIND_BY_MEDICATION = """
        SELECT *
          FROM TIS_MEDICATION_DISPENSE
         WHERE medicationID = :medicationId
           AND medicationDispenseStatus != 'DELETED'
    """;

    public static final String FIND_BY_DISPENSER = """
        SELECT *
          FROM TIS_MEDICATION_DISPENSE
         WHERE medicationDispenseDispensedBy = :staffId
           AND medicationDispenseStatus != 'DELETED'
    """;

    public static final String FIND_BY_DATE_RANGE = """
        SELECT *
          FROM TIS_MEDICATION_DISPENSE
         WHERE medicationDispenseDispensedAt BETWEEN :startDate AND :endDate
           AND medicationDispenseStatus != 'DELETED'
    """;
}
