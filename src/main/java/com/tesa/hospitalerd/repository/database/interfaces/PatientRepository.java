package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    void createPatient(Patient patient);
    List<Patient> findAllPatients();
    Optional<Patient> findPatientById(int id);
    List<Patient> patientSearch(String query);
    void updatePatient(Patient patient);
}
