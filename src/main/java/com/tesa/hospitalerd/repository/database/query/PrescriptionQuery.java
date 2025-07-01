package com.tesa.hospitalerd.repository.database.query;

public class PrescriptionQuery {
    public static final String CREATE_PRESCRIPTION = """
        INSERT INTO TIS_PRESCRIPTION
          (patientID,
           doctorID,
           prescriptionIssuedAt,
           prescriptionExpiryAt,
           prescriptionStatus,
           prescriptionCreatedAt,
           prescriptionUpdatedAt)
        VALUES
          (:patientID,
           :doctorID,
           :issuedAt,
           :validUntil,
           COALESCE(:status,'ACTIVE'),
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP)
    """;

    public static final String FIND_BY_ID = """
        SELECT *
          FROM TIS_PRESCRIPTION
         WHERE prescriptionID = :id
           AND prescriptionStatus != 'DELETED'
    """;

    public static final String UPDATE_PRESCRIPTION = """
        UPDATE TIS_PRESCRIPTION
           SET patientID             = COALESCE(:patientID, patientID),
               doctorID              = COALESCE(:doctorID, doctorID),
               prescriptionIssuedAt  = COALESCE(:issuedAt, prescriptionIssuedAt),
               prescriptionExpiryAt  = COALESCE(:validUntil, prescriptionExpiryAt),
               prescriptionStatus    = COALESCE(:status, prescriptionStatus),
               prescriptionUpdatedAt = CURRENT_TIMESTAMP
         WHERE prescriptionID        = :id
    """;

    public static final String DELETE_PRESCRIPTION = """
        UPDATE TIS_PRESCRIPTION
           SET prescriptionStatus    = 'DELETED',
               prescriptionUpdatedAt = CURRENT_TIMESTAMP
         WHERE prescriptionID        = :id
    """;

    public static final String FIND_BY_PATIENT = """
        SELECT *
          FROM TIS_PRESCRIPTION
         WHERE patientID = :patientId
           AND prescriptionStatus != 'DELETED'
    """;

    public static final String FIND_BY_DOCTOR = """
        SELECT *
          FROM TIS_PRESCRIPTION
         WHERE doctorID = :doctorId
           AND prescriptionStatus != 'DELETED'
    """;

    public static final String FIND_EXPIRED_UNFULFILLED = """
        SELECT *
          FROM TIS_PRESCRIPTION p
         WHERE p.prescriptionExpiryAt < :today
           AND p.prescriptionStatus != 'FULFILLED'
           AND p.prescriptionStatus != 'DELETED'
    """;
}
