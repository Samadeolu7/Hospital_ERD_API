package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.MedicationDispense;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationDispenseRepository;
import com.tesa.hospitalerd.repository.database.query.MedicationDispenseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

/**
 * JDBC implementation for MedicationDispenseRepository
 */
@Repository
public class MedicationDispenseRepositoryImpl implements MedicationDispenseRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MedicationDispenseRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createMedicationDispense(MedicationDispense medicationDispense) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("prescriptionItemID", medicationDispense.getPrescriptionItemID())
                .addValue("medicationID", medicationDispense.getMedicationID())
                .addValue("patientID", medicationDispense.getPatientID())
                .addValue("quantity", medicationDispense.getMedicationDispenseQuantity())
                .addValue("dispensedBy", medicationDispense.getMedicationDispenseDispensedBy())
                .addValue("dispensedAt", medicationDispense.getMedicationDispenseDispensedAt())
                .addValue("salePrice", medicationDispense.getMedicationDispenseSalePrice());
        return jdbcTemplate.update(MedicationDispenseQuery.CREATE_DISPENSE, params);
    }

    @Override
    public int updateMedicationDispense(MedicationDispense medicationDispense) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", medicationDispense.getMedicationDispenseID())
                .addValue("prescriptionItemID", medicationDispense.getPrescriptionItemID())
                .addValue("medicationID", medicationDispense.getMedicationID())
                .addValue("patientID", medicationDispense.getPatientID())
                .addValue("quantity", medicationDispense.getMedicationDispenseQuantity())
                .addValue("dispensedBy", medicationDispense.getMedicationDispenseDispensedBy())
                .addValue("dispensedAt", medicationDispense.getMedicationDispenseDispensedAt())
                .addValue("salePrice", medicationDispense.getMedicationDispenseSalePrice())
                .addValue("status", medicationDispense.getMedicationDispenseStatus());
        return jdbcTemplate.update(MedicationDispenseQuery.UPDATE_DISPENSE, params);
    }

    @Override
    public int deleteMedicationDispenseById(Long id) {
        return jdbcTemplate.update(
                MedicationDispenseQuery.DELETE_DISPENSE,
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public MedicationDispense findMedicationDispenseById(Long id) {
        return jdbcTemplate.queryForObject(
                MedicationDispenseQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(MedicationDispense.class)
        );
    }

    @Override
    public List<MedicationDispense> findMedicationDispenseByMedicationID(Long medicationId) {
        return jdbcTemplate.query(
                MedicationDispenseQuery.FIND_BY_MEDICATION,
                new MapSqlParameterSource("medicationId", medicationId),
                new BeanPropertyRowMapper<>(MedicationDispense.class)
        );
    }

    @Override
    public List<MedicationDispense> findByDispenserId(Long staffId) {
        return jdbcTemplate.query(
                MedicationDispenseQuery.FIND_BY_DISPENSER,
                new MapSqlParameterSource("staffId", staffId),
                new BeanPropertyRowMapper<>(MedicationDispense.class)
        );
    }

    @Override
    public List<MedicationDispense> findByDispensedAtBetween(LocalDate start, LocalDate end) {
        return jdbcTemplate.query(
                MedicationDispenseQuery.FIND_BY_DATE_RANGE,
                new MapSqlParameterSource()
                        .addValue("startDate", start)
                        .addValue("endDate", end),
                new BeanPropertyRowMapper<>(MedicationDispense.class)
        );
    }
}
