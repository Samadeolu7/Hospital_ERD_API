package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationDispense;

import java.time.LocalDate;
import java.util.List;

public interface MedicationDispenseService {
    MedicationDispense create(MedicationDispense dispense);
    MedicationDispense update(MedicationDispense dispense);
    void delete(Long id);
    MedicationDispense findById(Long id);
    List<MedicationDispense> findByMedicationId(Long medId);
    List<MedicationDispense> findByDispenserId(Long staffId);
    List<MedicationDispense> findByDateRange(LocalDate start, LocalDate end);
}
