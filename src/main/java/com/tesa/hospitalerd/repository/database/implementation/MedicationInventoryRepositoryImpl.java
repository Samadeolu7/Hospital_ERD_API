package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationInventoryRepository;
import com.tesa.hospitalerd.repository.database.query.MedicationInventoryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

/**
 * JDBC implementation for MedicationInventoryRepository
 */
@Repository
@Primary
public class MedicationInventoryRepositoryImpl implements MedicationInventoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MedicationInventoryRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createMedicationInventory(MedicationInventory medicationInventory) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationID", medicationInventory.getMedicationID())
                .addValue("location", medicationInventory.getMedicationInventoryLocation())
                .addValue("totalQuantity", medicationInventory.getMedicationInventoryTotalQuantity())
                .addValue("availableQuantity", medicationInventory.getMedicationInventoryAvailableQuantity())
                .addValue("batchNumber", medicationInventory.getMedicationInventoryBatchNo())
                .addValue("expiryDate", medicationInventory.getMedicationInventoryExpiryDate());
        return jdbcTemplate.update(MedicationInventoryQuery.CREATE_INVENTORY, params);
    }

    @Override
    public int updateMedicationInventory(MedicationInventory medicationInventory) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", medicationInventory.getMedicationID())
                .addValue("medicationID", medicationInventory.getMedicationID())
                .addValue("location", medicationInventory.getMedicationInventoryLocation())
                .addValue("totalQuantity", medicationInventory.getMedicationInventoryTotalQuantity())
                .addValue("availableQuantity", medicationInventory.getMedicationInventoryAvailableQuantity())
                .addValue("batchNumber", medicationInventory.getMedicationInventoryBatchNo())
                .addValue("expiryDate", medicationInventory.getMedicationInventoryID())
                .addValue("status", medicationInventory.getMedicationInventoryStatus());
        return jdbcTemplate.update(MedicationInventoryQuery.UPDATE_INVENTORY, params);
    }

    @Override
    public int deleteMedicationInventoryById(Long id) {
        return jdbcTemplate.update(
                MedicationInventoryQuery.DELETE_INVENTORY,
                new MapSqlParameterSource("id", id)
        );
    }



    @Override
    public MedicationInventory findMedicationInventoryById(Long id) {
        return jdbcTemplate.queryForObject(
                MedicationInventoryQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }

    @Override
    public List<MedicationInventory> findMedicationInventoryByMedicationID(Long medicationId) {
        return jdbcTemplate.query(
                MedicationInventoryQuery.FIND_BY_MEDICATION,
                new MapSqlParameterSource("medId", medicationId),
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }

    @Override
    public List<MedicationInventory> findByExpiryDateBefore(LocalDate date) {
        return jdbcTemplate.query(
                MedicationInventoryQuery.FIND_EXPIRED,
                new MapSqlParameterSource("date", date),
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }

    @Override
    public List<MedicationInventory> findAllMedicationInventory() {
        return jdbcTemplate.query(
                MedicationInventoryQuery.FIND_ALL,
                new MapSqlParameterSource(),
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }


}
