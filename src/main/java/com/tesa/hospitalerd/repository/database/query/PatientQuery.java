package com.tesa.hospitalerd.repository.database.query;

public class PatientQuery {

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