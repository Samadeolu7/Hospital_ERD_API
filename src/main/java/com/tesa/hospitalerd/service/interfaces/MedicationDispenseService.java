package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationDispense;
import com.tesa.hospitalerd.model.request.MedicationDispenseCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationDispenseUpdateRequest;

import java.time.LocalDate;
import java.util.List;

public interface MedicationDispenseService {
    MedicationDispense createMedicationDispense(MedicationDispenseCreateRequest req);

    MedicationDispense updateMedicationDispense(MedicationDispenseUpdateRequest req);

    void delete(Long id);
    MedicationDispense findById(Long id);
    List<MedicationDispense> findByMedicationId(Long medId);
    List<MedicationDispense> findByDispenserId(Long staffId);
    List<MedicationDispense> findByDateRange(LocalDate start, LocalDate end);
}
