package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.model.entity.Patient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * CRUD + inventory queries for MedicationInventory
 */
@Repository
public interface MedicationInventoryRepository{
    int createMedicationInventory(MedicationInventory medicationInventory);

    int updateMedicationInventory(MedicationInventory medicationInventory);

    int deleteMedicationInventoryById(Long id);

    MedicationInventory findMedicationInventoryById(Long id);

    List<MedicationInventory> findMedicationInventoryByMedicationID(Long medicationId);

    List<MedicationInventory> findByExpiryDateBefore(LocalDate date);

    List<MedicationInventory> findLowStock();

    interface PatientRepository {

        void createPatient(Patient patient);
        List<Patient> findAllPatients();
        Optional<Patient> findPatientById(int id);
        List<Patient> patientSearch(String query);
        void updatePatient(Patient patient);
    }
}

