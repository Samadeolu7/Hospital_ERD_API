package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.Prescription;
import com.tesa.hospitalerd.repository.database.interfaces.PrescriptionRepository;
import com.tesa.hospitalerd.repository.database.query.PrescriptionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;

/**
 * JDBC implementation for PrescriptionRepository
 */
@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PrescriptionRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPrescription(Prescription prescription) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientID", prescription.getPatientID())
                .addValue("doctorID", prescription.getDoctorID())
                .addValue("issuedAt", prescription.getPrescriptionIssuedAt())
                .addValue("validUntil", prescription.getPrescriptionExpiryAt());
        return jdbcTemplate.update(PrescriptionQuery.CREATE_PRESCRIPTION, params);
    }

    @Override
    public Prescription findPrescriptionByPrescriptionID(Long prescriptionID) {
        return jdbcTemplate.queryForObject(
                PrescriptionQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", prescriptionID),
                new BeanPropertyRowMapper<>(Prescription.class)
        );
    }

    @Override
    public int updatePrescription(Prescription prescription) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", prescription.getPrescriptionID())
                .addValue("patientID", prescription.getPatientID())
                .addValue("doctorID", prescription.getDoctorID())
                .addValue("issuedAt", prescription.getPrescriptionIssuedAt())
                .addValue("validUntil", prescription.getPrescriptionExpiryAt())
                .addValue("status", prescription.getPrescriptionStatus());
        return jdbcTemplate.update(PrescriptionQuery.UPDATE_PRESCRIPTION, params);
    }

    @Override
    public int deletePrescriptionByPrescriptionID(Long prescriptionID) {
        return jdbcTemplate.update(
                PrescriptionQuery.DELETE_PRESCRIPTION,
                new MapSqlParameterSource("id", prescriptionID)
        );
    }

    @Override
    public List<Prescription> findByPatientId(Long patientId) {
        return jdbcTemplate.query(
                PrescriptionQuery.FIND_BY_PATIENT,
                new MapSqlParameterSource("patientId", patientId),
                new BeanPropertyRowMapper<>(Prescription.class)
        );
    }

    @Override
    public List<Prescription> findByDoctorId(Long doctorId) {
        return jdbcTemplate.query(
                PrescriptionQuery.FIND_BY_DOCTOR,
                new MapSqlParameterSource("doctorId", doctorId),
                new BeanPropertyRowMapper<>(Prescription.class)
        );
    }

    @Override
    @Transactional
    public List<Prescription> findExpiredUnfulfilled() {
        return jdbcTemplate.query(
                PrescriptionQuery.FIND_EXPIRED_UNFULFILLED,
                new MapSqlParameterSource("today", LocalDate.now()),
                new BeanPropertyRowMapper<>(Prescription.class)
        );
    }
}
