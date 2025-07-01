package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.Equipment;
import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CRUD + custom methods for Medication
 */
@Repository
public interface MedicationRepository{
    int createMedication(Medication medication);

    int updateMedication(Medication medication);

    int deleteMedication(Long medicationId);

    Medication findMedicationById(Long medicationId);

    List<Medication>  findAllMedication();

    List<MedicationInventory> findAllMedicationInventory();

    List<Medication> findLowStock();

    List<PrescriptionItem> findPrescriptionItemByMedicationId(Long medicationId);

    List<Medication> findByRequiresRxTrue();

}

