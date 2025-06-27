package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.repository.database.interfaces.PrescriptionItemRepository;
import com.tesa.hospitalerd.repository.database.query.PrescriptionItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JDBC implementation for PrescriptionItemRepository
 */
@Repository
public class PrescriptionItemRepositoryImpl implements PrescriptionItemRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PrescriptionItemRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPrescriptionItem(PrescriptionItem item) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("prescriptionID", item.getPrescriptionID())
                .addValue("medicationID", item.getMedicationID())
                .addValue("dosage", item.getPrescriptionID())
                .addValue("quantity", item.getPrescriptionItemQuantity())
                .addValue("instructions", item.getPrescriptionItemInstructions());
        return jdbcTemplate.update(PrescriptionItemQuery.CREATE_ITEM, params);
    }

    @Override
    public int updatePrescriptionItem(PrescriptionItem item) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", item.getPrescriptionItemID())
                .addValue("prescriptionID", item.getPrescriptionID())
                .addValue("medicationID", item.getMedicationID())
                .addValue("dosage", item.getPrescriptionItemDosage())
                .addValue("quantity", item.getPrescriptionItemQuantity())
                .addValue("instructions", item.getPrescriptionItemInstructions())
                .addValue("status", item.getPrescriptionItemStatus());
        return jdbcTemplate.update(PrescriptionItemQuery.UPDATE_ITEM, params);
    }

    @Override
    public PrescriptionItem findPrescriptionItemById(Long id) {
        return jdbcTemplate.queryForObject(
                PrescriptionItemQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(PrescriptionItem.class)
        );
    }

    @Override
    public int countByPrescriptionId(Long prescriptionId) {
        return jdbcTemplate.queryForObject(
                PrescriptionItemQuery.COUNT_BY_PRESCRIPTION,
                new MapSqlParameterSource("prescriptionId", prescriptionId),
                Integer.class
        );
    }

    @Override
    @Transactional
    public int deleteByPrescriptionId(Long prescriptionId) {
        return jdbcTemplate.update(
                PrescriptionItemQuery.DELETE_BY_PRESCRIPTION,
                new MapSqlParameterSource("prescriptionId", prescriptionId)
        );
    }

    @Override
    public List<PrescriptionItem> findByPrescriptionId(Long prescriptionId) {
        return jdbcTemplate.query(
                PrescriptionItemQuery.FIND_BY_PRESCRIPTION,
                new MapSqlParameterSource("prescriptionId", prescriptionId),
                new BeanPropertyRowMapper<>(PrescriptionItem.class)
        );
    }
}
