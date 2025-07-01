
package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationRepository;
import com.tesa.hospitalerd.repository.database.query.MedicationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * JDBC implementation for MedicationRepository
 */
@Repository
@Primary
public class MedicationRepositoryImpl implements MedicationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MedicationRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createMedication(Medication medication) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", medication.getMedicationName())
                .addValue("description", medication.getMedicationDescription())
                .addValue("requiresRx", medication.getMedRequiresRX())
                .addValue("unit", medication.getMedicationUnit())
                .addValue("reorderLevel", medication.getMedicationReorderLevel());
        return jdbcTemplate.update(MedicationQuery.CREATE_MEDICATION, params);
    }

    @Override
    public int updateMedication(Medication medication) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", medication.getMedicationID())
                .addValue("name", medication.getMedicationName())
                .addValue("description", medication.getMedicationDescription())
                .addValue("requiresRx", medication.getMedRequiresRX())
                .addValue("unit", medication.getMedicationUnit())
                .addValue("reorderLevel", medication.getMedicationReorderLevel())
                .addValue("status", medication.getMedicationStatus());
        return jdbcTemplate.update(MedicationQuery.UPDATE_MEDICATION, params);
    }

    @Override
    public int deleteMedication(Long medicationId) {
        return jdbcTemplate.update(
                MedicationQuery.DELETE_MEDICATION,
                new MapSqlParameterSource("id", medicationId)
        );
    }

    @Override
    public Medication findMedicationById(Long medicationId) {
        return jdbcTemplate.queryForObject(
                MedicationQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", medicationId),
                new BeanPropertyRowMapper<>(Medication.class)
        );
    }

    @Override
    public List<Medication> findAllMedication() {
        return jdbcTemplate.query(
                MedicationQuery.FIND_ALL,
                new BeanPropertyRowMapper<>(Medication.class)
        );
    }

    @Override
    public List<MedicationInventory> findAllMedicationInventory() {
        return List.of();
    }

    @Override
    public List<Medication> findLowStock() {
        return jdbcTemplate.query(
                MedicationQuery.FIND_LOW_STOCK,
                new BeanPropertyRowMapper<>(Medication.class)
        );
    }

    @Override
    public List<PrescriptionItem> findPrescriptionItemByMedicationId(Long medicationId) {
        return jdbcTemplate.query(
                MedicationQuery.FIND_PRESCRIPTION_ITEMS,
                new MapSqlParameterSource("medicationId", medicationId),
                new BeanPropertyRowMapper<>(PrescriptionItem.class)
        );
    }

    @Override
    public List<Medication> findByRequiresRxTrue() {
        return jdbcTemplate.query(
                MedicationQuery.FIND_REQUIRES_RX,
                new BeanPropertyRowMapper<>(Medication.class)
        );
    }
}

