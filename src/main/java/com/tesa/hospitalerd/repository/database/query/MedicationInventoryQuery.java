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

    public static class EquipmentQuery {

        public static final String INSERT_EQUIPMENT = """
                INSERT INTO TIS_EQUIPMENT
                (
                equipmentName,
                equipmentSerialNumber,
                equipmentPurchaseDate,
                equipmentLastMaintenanceDate,
                equipmentStatus,
                equipmentCreatedAt,
                equipmentUpdated
                )
                VALUES
                (
                :equipmentName,
                :equipmentSerialNumber,
                :equipmentPurchaseDate,
                :equipmentLastMaintenanceDate,
                COALESCE(:equipmentStatus, 'AVAILABLE')
                CURRENT_TIMESTAMP,
                CURRENT_TIMESTAMP)
                """;

        public static final String FIND_ALL_EQUIPMENTS = """
                SELECT *
                FROM TIS_EQUIPMENT
                """;

        public static final String FIND_EQUIPMENT_BY_ID = """
                SELECT *
                FROM TIS_EQUIPMENT
                WHERE equipmentId = :equipmentId
                """;

        public static final String SEARCH_EQUIPMENT = """
                SELECT *
                FROM TIS_EQUIPMENT
                WHERE equipmentName LIKE :query
                OR equipmentSerialNumber LIKE :query
                OR equipmentLastMaintenance LIKE :query
                OR equipmentStatus LIKE :query
                """;

        public static final String FIND_EQUIPMENTS_BY_STATUS = """
                SELECT *
                FROM TIS_EQUIPMENT
                WHERE equipmentStatus = :equipmentStatus
                """;

        public static final String MARK_EQUIPMENT_FOR_MAINTENANCE = """
                UPDATE TIS_EQUIPMENT
                SET
                    equipmentStatus = 'MAINTENANCE',
                WHERE equipmentId = :equipmentId
                """;

        public static final String MARK_EQUIPMENT_AS_AVAILABLE = """
                UPDATE TIS_EQUIPMENT
                SET
                    equipmentStatus = 'AVAILABLE',
                    equipmentLastMaintenanceDate = CURRENT_DATE
                WHERE id = :id
                """;

        public static final String DECOMMISSION_EQUIPMENT = """
                UPDATE TIS_EQUIPMENT
                SET equipmentStatus = 'DECOMMISSIONED'
                WHERE equipmentId = :equipmentId""";

        public static final String UPDATE_EQUIPMENT = """
                UPDATE TIS_EQUIPMENT
                SET equipmentName = COALESCE(:equipmentName, equipmentName),
                    equipmentSerialNumber = COALESCE(:equipmentSerialNumber, equipmentSerialNumber),
                    equipmentLastMaintenanceDate = COALESCE(:equipmentLastMaintenanceDate, equipmentLastMaintenanceDate),
                    equipmentStatus = COALESCE(:equipmentStatus, equipmentStatus),
                    equipment = COALESCE(:equipment, equipment),
                    equipmentUpdatedAt = CURRENT_TIMESTAMP
                WHERE equipmentId = :equipmentId
                """;

    }

    public static class PatientQuery {

        public static final String INSERT_PATIENT = """
                INSERT INTO TIS_PATIENT
                (patientFirstName,
                patientLastName,
                patientDOB,
                patientGender,
                patientBloodType,
                patientGenotype,
                patientAddress,
                patientPhoneNumber,
                patientEmail,
                patientAge,
                patientNextOfKin,
                patientCreatedAt,
                patientUpdatedAt)
                VALUES
                (:patientFirstName,
                :patientLastName,
                :patientDOB,
                :patientGender,
                :patientBloodType,
                :patientGenotype,
                :patientAddress,
                :patientPhoneNumber,
                :patientEmail,
                :patientAge,
                :patientNextOfKin,
                CURRENT_TIMESTAMP,
                CURRENT_TIMESTAMP)
                """;

        public static final String FIND_ALL_PATIENT = """
                SELECT *
                FROM TIS_PATIENT
                """;

        public static final String FIND_PATIENT_BY_ID = """
                SELECT *
                FROM TIS_PATIENT
                WHERE patientId = :patientId
                """;

        public static final String SEARCH_PATIENT = """
                SELECT *
                FROM TIS_PATIENT
                WHERE patientFirstName LIKE :query
                OR patientLastName  LIKE :query
                OR patientDOB LIKE :query
                OR patientGender LIKE :query
                OR patientBloodType LIKE :query
                OR patientGenotype LIKE :query
                OR patientAddress LIKE :query
                OR patientPhoneNumber LIKE :query
                OR patientEmail LIKE :query
                """;

        public static final String UPDATE_PATIENT = """
            UPDATE TIS_PATIENT
            SET patientFirstName = COALESCE(:patientFirstName, patientFirstName),
                patientLastName = COALESCE(:patientLastName, patientLastName),
                patientDOB = COALESCE(:patientDOB, patientDOB),
                patientGender = COALESCE(:patientGender, patientGender),
                patientBloodType = COALESCE(:patientBloodType, patientBloodType),
                patientGenotype = COALESCE(:patientGenotype, patientGenotype),
                patientAddress = COALESCE(:patientAddress, patientAddress),
                patientPhoneNumber = COALESCE(:patientPhoneNumber, patientPhoneNumber),
                patientAge = COALESCE(:patientAge, patientAge),
                patientUpdatedAt = CURRENT_TIMESTAMP
            WHERE patientId = :patientId
            """;
    }
}
