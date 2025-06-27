// Package: com.tesa.hospitalerd.repository.database.query

package com.tesa.hospitalerd.repository.database.query;

public class MedicationQuery {
    public static final String CREATE_MEDICATION = """
        INSERT INTO TIS_MEDICATION
          (medicationName,
           medicationDescription,
           medRequiresRX,
           medicationUnit,
           medicationReorderLevel,
           medicationStatus,
           medicationCreatedAt,
           medicationUpdatedAt)
        VALUES
          (:name,
           :description,
           :requiresRx,
           :unit,
           :reorderLevel,
           COALESCE(:status,'ACTIVE'),
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP)
    """;

    public static final String UPDATE_MEDICATION = """
        UPDATE TIS_MEDICATION
           SET medicationName         = COALESCE(:name, medicationName),
               medicationDescription  = COALESCE(:description, medicationDescription),
               medRequiresRX          = COALESCE(:requiresRx, medRequiresRX),
               medicationUnit         = COALESCE(:unit, medicationUnit),
               medicationReorderLevel = COALESCE(:reorderLevel, medicationReorderLevel),
               medicationStatus       = COALESCE(:status, medicationStatus),
               medicationUpdatedAt    = CURRENT_TIMESTAMP
         WHERE medicationID           = :id
    """;

    public static final String DELETE_MEDICATION = """
        UPDATE TIS_MEDICATION
           SET medicationStatus       = 'DELETED',
               medicationUpdatedAt    = CURRENT_TIMESTAMP
         WHERE medicationID           = :id
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_MEDICATION
         WHERE medicationID = :id
           AND medicationStatus != 'DELETED'
    """;

    public static final String FIND_PRESCRIPTION_ITEMS = """
        SELECT *
          FROM TIS_PRESCRIPTION_ITEM
         WHERE medicationID = :medicationId
    """;

    public static final String FIND_REQUIRES_RX = """
        SELECT *
          FROM TIS_MEDICATION
         WHERE medRequiresRX = TRUE
           AND medicationStatus != 'DELETED'
    """;
}
