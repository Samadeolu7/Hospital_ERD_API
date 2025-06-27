package com.tesa.hospitalerd.repository.query;

public class PatientQuery {

    public static final String INSERT_PATIENT = """
            INSERT INTO () 
            VALUES ()""";

    public static final String FIND_ALL_PATIENT = """
            SELECT * FROM""";

    public static final String FIND_PATIENT_BY_ID = """
            SELECT * FROM""";

    public static final String SEARCH_PATIENT = """
            SELECT * FROM""";

    public static final String UPDATE_PATIENT = """
        UPDATE 
        SET studentFirstName = :studentFirstName,
            studentLastName = :studentLastName,
            studentStateOfOrigin = :studentStateOfOrigin,
            studentAge = :studentAge,
        WHERE studentId = :studentId""";
}
