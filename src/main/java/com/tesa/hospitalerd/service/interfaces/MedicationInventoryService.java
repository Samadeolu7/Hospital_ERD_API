package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationInventory;

import java.time.LocalDate;
import java.util.List;

public interface MedicationInventoryService {
    MedicationInventory create(MedicationInventory inventory);
    MedicationInventory update(MedicationInventory inventory);
    void delete(Long id);
    MedicationInventory findById(Long id);
    List<MedicationInventory> findByMedicationId(Long medId);
    List<MedicationInventory> findExpiringBefore(LocalDate date);
    List<MedicationInventory> findLowStock();
}

