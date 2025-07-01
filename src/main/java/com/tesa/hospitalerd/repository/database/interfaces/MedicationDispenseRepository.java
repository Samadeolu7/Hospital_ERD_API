package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationDispense;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * MedicationDispense access
 */
@Repository
public interface MedicationDispenseRepository{
    int createMedicationDispense(MedicationDispense medicationDispense);

    int updateMedicationDispense(MedicationDispense medicationDispense);

    int  deleteMedicationDispenseById(Long id);

    MedicationDispense findMedicationDispenseById(Long id);

    List<MedicationDispense> findMedicationDispenseByMedicationID(Long medicationId);

    List<MedicationDispense> findByDispenserId(Long staffId);

    List<MedicationDispense> findByDispensedAtBetween(LocalDate start, LocalDate end);
    List<MedicationDispense> findAllMedicationDispense();

}

