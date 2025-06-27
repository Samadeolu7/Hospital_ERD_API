package com.tesa.hospitalerd.repository.query;

public class StaffQuery {
    
    public static final String  INSERT_STAFF = """
            INSERT INTO(staffFirstName, staffLastName, staffRole, staffSpecialization, staffDepartment, staffPhoneNumber,
            staffEmail, staffStatus)
            VALUES(:staffFirstName, :staffLastName, :staffRole, :staffSpecialization, :staffDepartment, :staffPhoneNumber,
            :staffEmail, :staffStatus)""";

    public static final String FIND_ALL_STAFF = """
            SELECT * FROM WHERE""";
    
    public static final String FIND_ALL_DOCTORS = """
            SELECT * FROM WHERE role = 'DOCTOR'""";

    public static final String FIND_AVAILABLE_DOCTORS = """
            SELECT * FROM WHERE role = 'DOCTOR'""";

    public static final String FIND_STAFF_BY_ID = """
            SELECT * FROM WHERE role = 'DOCTOR'""";

    public static final String SEARCH_STAFF = """
            SELECT * FROM WHERE role = 'DOCTOR'""";

    public static final String UPDATE_STAFF = """
            SELECT * FROM WHERE role = 'DOCTOR'""";
    
}
