package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;

import java.util.List;

public interface MedicationService {
    Medication create(Medication medication);
    Medication update(Medication medication);
    void delete(Long id);
    Medication findById(Long id);
    List<PrescriptionItem> findPrescriptionItems(Long medId);
    List<Medication> findRequiresRx();
}
