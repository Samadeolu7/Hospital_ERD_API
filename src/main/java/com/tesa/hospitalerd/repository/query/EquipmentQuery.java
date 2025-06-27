package com.tesa.hospitalerd.repository.query;

public class EquipmentQuery {

    public static final String INSERT_EQUIPMENT = """
            INSERT INTO () 
            VALUES ()""";

    public static final String FIND_ALL_EQUIPMENTS = """
            """;

    public static final String FIND_EQUIPMENT_BY_ID = """
            SELECT * FROM""";

    public static final String SEARCH_EQUIPMENT = """
            """;

    public static final String FIND_EQUIPMENTS_BY_STATUS = """
            """;

    public static final String MARK_EQUIPMENT_FOR_MAINTENANCE = """
            UPDATE equipment
            SET 
                status = 'MAINTENANCE',
            WHERE id = :id""";

    public static final String MARK_EQUIPMENT_AS_AVAILABLE = """
            UPDATE equipment
            SET 
                status = 'AVAILABLE',
                last_maintenance_date = CURRENT_DATE
            WHERE id = :id""";

    public static final String DECOMMISSION_EQUIPMENT = """
            UPDATE equipment
            SET status = 'DECOMMISSIONED'
            WHERE id = :id""";

    public static final String UPDATE_EQUIPMENT = """
            """;

}
