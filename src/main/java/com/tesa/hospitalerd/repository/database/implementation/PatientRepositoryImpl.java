package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.Patient;
import com.tesa.hospitalerd.repository.database.interfaces.PatientRepository;
import com.tesa.hospitalerd.repository.database.query.PatientQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PatientRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPatient(Patient patient) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientFirstName", patient.getPatientFirstName())
                .addValue("patientLastname", patient.getPatientLastName())
                .addValue("patientDOB", patient.getPatientDOB())
                .addValue("patientGender", patient.getPatientGender())
                .addValue("patientBloodType", patient.getPatientBloodType())
                .addValue("patientGenotype", patient.getPatientGenotype())
                .addValue("patientAddress", patient.getPatientAddress())
                .addValue("patientPhoneNumber", patient.getPatientPhoneNumber())
                .addValue("patientEmail", patient.getPatientEmail())
                .addValue("patientAge", patient.getPatientAge())
                .addValue("patientNextOfKin", patient.getPatientNextOfKin());
        jdbcTemplate.update(PatientQuery.INSERT_PATIENT, params);
    }

    @Override
    public List<Patient> findAllPatients() {
        return jdbcTemplate.query(PatientQuery.FIND_ALL_PATIENT, new BeanPropertyRowMapper<>(Patient.class));
    }

    @Override
    public Optional<Patient> findPatientById(int id) {
        try {
            Patient patient = jdbcTemplate.queryForObject(
                    PatientQuery.FIND_PATIENT_BY_ID,
                    new MapSqlParameterSource("patientId", id),
                    new BeanPropertyRowMapper<>(Patient.class));
            return Optional.ofNullable(patient);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Patient> patientSearch(String query) {
        return jdbcTemplate.query(
                PatientQuery.SEARCH_PATIENT,
                new MapSqlParameterSource("query", "%" + query + "%"),
                new BeanPropertyRowMapper<>(Patient.class));
    }

    @Override
    public void updatePatient(Patient patient) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", patient.getPatientId())
                .addValue("patientFirstName", patient.getPatientFirstName())
                .addValue("patientLastname", patient.getPatientLastName())
                .addValue("patientDOB", patient.getPatientDOB())
                .addValue("patientGender", patient.getPatientGender())
                .addValue("patientBloodType", patient.getPatientBloodType())
                .addValue("patientGenotype", patient.getPatientGenotype())
                .addValue("patientAddress", patient.getPatientAddress())
                .addValue("patientPhoneNumber", patient.getPatientPhoneNumber())
                .addValue("patientEmail", patient.getPatientEmail())
                .addValue("patientAge", patient.getPatientAge())
                .addValue("patientNextOfKin", patient.getPatientNextOfKin());
        jdbcTemplate.update(PatientQuery.UPDATE_PATIENT, params);
    }
}