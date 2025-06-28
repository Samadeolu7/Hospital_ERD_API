
package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.entity.Patient;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationInventoryRepository;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationRepository;
import com.tesa.hospitalerd.repository.database.query.MedicationInventoryQuery;
import com.tesa.hospitalerd.repository.database.query.MedicationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation for MedicationRepository
 */
@Repository
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

    @Repository
    public static class PatientRepositoryImpl implements MedicationInventoryRepository.PatientRepository {

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
                    .addValue("patientAge", patient.getAge())
                    .addValue("patientNextOfKin", patient.getPatientNextOfKin());
            jdbcTemplate.update(MedicationInventoryQuery.PatientQuery.INSERT_PATIENT, params);
        }

        @Override
        public List<Patient> findAllPatients() {
            return jdbcTemplate.query(MedicationInventoryQuery.PatientQuery.FIND_ALL_PATIENT, new BeanPropertyRowMapper<>(Patient.class));
        }

        @Override
        public Optional<Patient> findPatientById(int id) {
            try {
                Patient patient = jdbcTemplate.queryForObject(
                        MedicationInventoryQuery.PatientQuery.FIND_PATIENT_BY_ID,
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
                    MedicationInventoryQuery.PatientQuery.SEARCH_PATIENT,
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
                    .addValue("patientAge", patient.getAge())
                    .addValue("patientNextOfKin", patient.getPatientNextOfKin());
            jdbcTemplate.update(MedicationInventoryQuery.PatientQuery.UPDATE_PATIENT, params);
        }
    }
}

