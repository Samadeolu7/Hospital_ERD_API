package com.tesa.hospitalerd.repository.query;

public class EquipmentQuery {

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
