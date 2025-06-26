package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PrescriptionItem access
 */
@Repository
public interface PrescriptionItemRepository {
    int createPrescriptionItem(PrescriptionItem prescriptionItem);

    int updatePrescriptionItem(PrescriptionItem prescriptionItem);

    PrescriptionItem findPrescriptionItemById(Long id);

    int countByPrescriptionId(Long prescriptionId);

    int deleteByPrescriptionId(Long prescriptionId);

    List<PrescriptionItem> findByPrescriptionId(Long prescriptionId);
}
