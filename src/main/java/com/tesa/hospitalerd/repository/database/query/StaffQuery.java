package com.tesa.hospitalerd.repository.database.query;

public class StaffQuery {
    
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