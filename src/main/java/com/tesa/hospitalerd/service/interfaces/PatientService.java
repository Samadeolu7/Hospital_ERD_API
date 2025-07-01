package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.request.PatientRequest;
import com.tesa.hospitalerd.model.entity.Patient;

import java.util.List;

public interface PatientService {

    Patient createPatient(PatientRequest request);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    List<Patient> searchPatient(String query);
    Patient updatePatient(Long id, PatientRequest request);
}