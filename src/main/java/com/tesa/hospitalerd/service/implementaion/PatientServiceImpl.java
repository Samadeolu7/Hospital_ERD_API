package com.tesa.hospitalerd.service.implementaion;

import com.google.gson.Gson;
import com.tesa.hospitalerd.model.entity.Patient;
import com.tesa.hospitalerd.model.request.PatientRequest;
import com.tesa.hospitalerd.repository.database.interfaces.PatientRepository;
import com.tesa.hospitalerd.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    Gson gson = new Gson();

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void createPatient(PatientRequest request) {
        try {
            var patient = gson.fromJson(gson.toJson(request), Patient.class);

            System.out.println("PATIENT NAME >>");
            System.out.println(patient.getPatientFirstName());

            System.out.println("PATIENT Id >>");
            System.out.println(patient.getPatientId());

            patientRepository.createPatient(patient);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create patients: " + e.getMessage(), e);
        }

    }

    @Override
    public List<Patient> getAllPatients() {
        try {
            return patientRepository.findAllPatients();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get patients: " + e.getMessage(), e);
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            return patientRepository.findPatientById(id)
                    .orElseThrow(() -> new NoSuchElementException("Patient  with id: " + id + " not found"));
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get patient: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Patient> searchPatient(String query) {
        try {
            return patientRepository.patientSearch(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to search patient: " + e.getMessage(), e);
        }
    }

    @Override
    public void updatePatient(Long id, PatientRequest request) {
        try {
            Patient existingPatient = patientRepository.findPatientById(id)
                    .orElseThrow(() -> new NoSuchElementException("Patient  with id: " + id + " not found"));

            Patient updatedPatient = gson.fromJson(gson.toJson(request), Patient.class);
            updatedPatient.setPatientId(id);
            patientRepository.updatePatient(updatedPatient);

        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update patient: " + e.getMessage(), e);
        }
    }
}
