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

    public static class StaffQuery {

        public static final String  INSERT_STAFF = """
                INSERT INTO TIS_STAFF
                (staffFirstName,
                staffLastName,
                staffRole,
                staffSpecialization,
                staffDepartment,
                staffPhoneNumber,
                staffEmail,
                staffStatus
                staffCreatedAt
                staffUpdated)
                VALUES
                (:staffFirstName,
                :staffLastName,
                :staffRole,
                :staffSpecialization,
                :staffDepartment,
                :staffPhoneNumber,
                :staffEmail,
                COALESCE(:staffStatus, 'OFF_DUTY'),
                CURRENT_TIMESTAMP,
                CURRENT_TIMESTAMP)
                """;

        public static final String FIND_ALL_STAFF = """
                SELECT *
                FROM TIS_STAFF
                """;

        public static final String FIND_ALL_DOCTORS = """
                SELECT *
                FROM TIS_STAFF
                WHERE staffRole = 'DOCTOR'
                """;

        public static final String FIND_AVAILABLE_DOCTORS = """
                SELECT *
                FROM TIS_STAFF 
                WHERE staffRole = 'DOCTOR'
                AND staffStatus = 'ON_DUTY'
                AND staffId NOT IN (
                SELECT staffId
                FROM TIS_APPOINTMENTS
                WHERE DATE(appointmentDate) = :appointmentDate
                AND TIME(appointmentTime) BETWEEN
                SUBTIME(:time, '00:30:00')
                AND ADDTIME(:time, '01:00:00')
                """;


        public static final String FIND_STAFF_BY_ID = """
                SELECT *
                FROM TIS_STAFF
                WHERE staffId = :staffId
                """;

        public static final String SEARCH_STAFF = """
                SELECT *
                FROM TIS_STAFF
                WHERE staffFirstName LIKE :query
                OR staffLastName LIKE :query
                OR staffRole LIKE :query
                OR staffSpecialization LIKE :query
                OR staffDepartment LIKE :query
                OR staffPhoneNumber LIKE :query
                OR staffEmail LIKE :query
                OR staffStatus LIKE :query
                """;

        public static final String UPDATE_STAFF = """
                UPDATE TIS_STAFF
                SET staffFirstName = COALESCE(:staffFirstName, staffFirstName),
                    staffLastName = COALESCE(:staffLastName, staffLastName),
                    staffRole = COALESCE(:staffRole, staffRole),
                    staffSpecialization = COALESCE(:staffSpecialization, staffSpecialization),
                    staffDepartment = COALESCE(:staffDepartment, staffDepartment),
                    staffPhoneNumber = COALESCE(:staffPhoneNumber, staffPhoneNumber),
                    staffEmail = COALESCE(:staffEmail, staffEmail),
                    staffStatus = COALESCE(:staffStatus, staffStatus)
                    staffUpdatedAt = CURRENT_TIMESTAMP
                WHERE staffId = :staffId
                """;

    }
}
