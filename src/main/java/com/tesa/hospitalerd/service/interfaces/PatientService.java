package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.request.PatientRequest;
import com.tesa.hospitalerd.model.entity.Patient;

import java.util.List;

public interface PatientService {

    void createPatient(PatientRequest request);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    List<Patient> searchPatient(String query);
    void updatePatient(Long id, PatientRequest request);
}