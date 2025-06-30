package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.model.request.MedicationInventoryCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationInventoryUpdateRequest;

import java.time.LocalDate;
import java.util.List;

public interface MedicationInventoryService {
    MedicationInventory createMedicationInventory(MedicationInventoryCreateRequest req);

    MedicationInventory updateMedicationInventory(MedicationInventoryUpdateRequest req);

    void delete(Long id);
    MedicationInventory findById(Long id);
    List<MedicationInventory> findByMedicationId(Long medId);
    List<MedicationInventory> findExpiringBefore(LocalDate date);
    List<MedicationInventory> findAll();
}

