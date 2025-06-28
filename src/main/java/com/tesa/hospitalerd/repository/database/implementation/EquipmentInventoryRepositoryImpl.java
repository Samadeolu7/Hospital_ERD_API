package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.EquipmentInventory;
import com.tesa.hospitalerd.repository.database.interfaces.EquipmentInventoryRepository;
import com.tesa.hospitalerd.repository.database.query.EquipmentInventoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * JDBC implementation for EquipmentInventoryRepository
 */

@Repository
@Primary
public class EquipmentInventoryRepositoryImpl implements EquipmentInventoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EquipmentInventoryRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createEquipmentInventory(EquipmentInventory equipmentInventory) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentID", equipmentInventory.getEquipmentID())
                .addValue("totalQuantity", equipmentInventory.getEquipmentTotalQuantity())
                .addValue("availableQuantity", equipmentInventory.getEquipmentAvailableQuantity())
                .addValue("location", equipmentInventory.getEquipmentInventoryLocation())
                .addValue("reorderLevel", equipmentInventory.getEquipmentInventoryReorderLevel());
        return jdbcTemplate.update(EquipmentInventoryQuery.CREATE_INVENTORY, params);
    }

    @Override
    public int updateEquipmentInventory(EquipmentInventory equipmentInventory) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", equipmentInventory.getEquipmentInventoryID())
                .addValue("equipmentID", equipmentInventory.getEquipmentID())
                .addValue("totalQuantity", equipmentInventory.getEquipmentTotalQuantity())
                .addValue("availableQuantity", equipmentInventory.getEquipmentAvailableQuantity())
                .addValue("location", equipmentInventory.getEquipmentInventoryLocation())
                .addValue("reorderLevel", equipmentInventory.getEquipmentInventoryReorderLevel())
                .addValue("status", equipmentInventory.getEquipmentInventoryStatus());
        return jdbcTemplate.update(EquipmentInventoryQuery.UPDATE_INVENTORY, params);
    }

    @Override
    public int deleteEquipmentInventory(Long id) {
        return jdbcTemplate.update(
                EquipmentInventoryQuery.DELETE_INVENTORY,
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public EquipmentInventory findEquipmentInventoryById(Long id) {
        return jdbcTemplate.queryForObject(
                EquipmentInventoryQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(EquipmentInventory.class)
        );
    }

    @Override
    public List<EquipmentInventory> findAllEquipmentInventory() {
        return jdbcTemplate.query(
                EquipmentInventoryQuery.FIND_ALL,
                new BeanPropertyRowMapper<>(EquipmentInventory.class)
        );
    }

    @Override
    public List<EquipmentInventory> findByAvailableQuantityLessThan(int reorderLevel) {
        return jdbcTemplate.query(
                EquipmentInventoryQuery.FIND_LOW_STOCK,
                new MapSqlParameterSource("reorderLevel", reorderLevel),
                new BeanPropertyRowMapper<>(EquipmentInventory.class)
        );
    }
}