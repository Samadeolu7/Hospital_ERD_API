package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.Equipment;
import com.tesa.hospitalerd.repository.database.interfaces.EquipmentRepository;
import com.tesa.hospitalerd.repository.database.query.EquipmentQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EquipmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createEquipment(Equipment equipment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentName", equipment.getEquipmentName())
                .addValue("equipmentSerialNumber", equipment.getEquipmentSerialNumber())
                .addValue("equipmentPurchaseDate", equipment.getEquipmentPurchaseDate())
                .addValue("equipmentLastMaintenanceDate", equipment.getEquipmentLastMaintenanceDate())
                .addValue("equipmentStatus", equipment.getEquipmentStatus());
        jdbcTemplate.update(EquipmentQuery.INSERT_EQUIPMENT, params);
    }

    @Override
    public List<Equipment> findAllEquipments() {
        return jdbcTemplate.query(EquipmentQuery.FIND_ALL_EQUIPMENTS, new BeanPropertyRowMapper<>(Equipment.class));
    }

    @Override
    public Optional<Equipment> findEquipmentById(Long id) {
        try {
             Equipment equipment = jdbcTemplate.queryForObject(
                    EquipmentQuery.FIND_EQUIPMENT_BY_ID,
                    new MapSqlParameterSource("equipmentId", id),
                    new BeanPropertyRowMapper<>(Equipment.class));
            return Optional.ofNullable(equipment);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Equipment> equipmentSearch(String query) {
        return jdbcTemplate.query(
                EquipmentQuery.SEARCH_EQUIPMENT,
                new MapSqlParameterSource("query", "%" + query + "%"),
                new BeanPropertyRowMapper<>(Equipment.class));
    }

    @Override
    public List<Equipment> findEquipmentByStatus(String status) {
        return jdbcTemplate.query(
                EquipmentQuery.FIND_EQUIPMENTS_BY_STATUS,
                new MapSqlParameterSource("equipmentStatus", status),
                new BeanPropertyRowMapper<>(Equipment.class));
    }

    @Override
    public void markEquipmentForMaintenance(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentId", id)
                .addValue("equipmentStatus", "MAINTENANCE");
        jdbcTemplate.update(EquipmentQuery.MARK_EQUIPMENT_FOR_MAINTENANCE, params);
    }

    @Override
    public void markEquipmentAsAvailable(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentId", id)
                .addValue("maintenanceDate", new Date())
                .addValue("equipmentStatus", "AVAILABLE");
        jdbcTemplate.update(EquipmentQuery.MARK_EQUIPMENT_AS_AVAILABLE, params);
    }

    @Override
    public void decommissionEquipment(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentId", id)
                .addValue("equipmentStatus", "DECOMMISSIONED");
        jdbcTemplate.update(EquipmentQuery.DECOMMISSION_EQUIPMENT, params);
    }

    @Override
    public void updateEquipment(Equipment equipment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentId", equipment.getEquipmentId())
                .addValue("equipmentName", equipment.getEquipmentName())
                .addValue("equipmentSerialNumber", equipment.getEquipmentSerialNumber())
                .addValue("equipmentPurchaseDate", equipment.getEquipmentPurchaseDate())
                .addValue("equipmentLastMaintenanceDate", equipment.getEquipmentLastMaintenanceDate())
                .addValue("equipmentStatus", equipment.getEquipmentStatus());
        jdbcTemplate.update(EquipmentQuery.UPDATE_EQUIPMENT, params);
    }
}
