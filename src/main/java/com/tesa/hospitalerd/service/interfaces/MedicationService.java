package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.model.request.MedicationCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationUpdateRequest;

import java.util.List;

public interface MedicationService {
    Medication createMedication(MedicationCreateRequest req);

    Medication updateMedication(MedicationUpdateRequest req);

    void delete(Long id);
    Medication findById(Long id);
    List<Medication> findAll();
    List<PrescriptionItem> findPrescriptionItems(Long medId);
    List<Medication> findRequiresRx();
    List<Medication> findLowStock();
    void recalculateQuantities(Long medicationId);
    void adjustAvailableQuantity(Long medicationId, int delta);
    void adjustTotalQuantity(Long medicationId, int delta);
}
