package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationInventory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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
}

