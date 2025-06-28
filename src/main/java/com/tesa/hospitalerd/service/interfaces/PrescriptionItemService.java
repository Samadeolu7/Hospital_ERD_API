package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.PrescriptionItem;

import java.util.List;

public interface PrescriptionItemService {
    PrescriptionItem create(PrescriptionItem item);
    PrescriptionItem update(PrescriptionItem item);
    void deleteByPrescriptionId(Long prescriptionId);
    PrescriptionItem findById(Long id);
    int countByPrescriptionId(Long prescriptionId);
    List<PrescriptionItem> findByPrescriptionId(Long prescriptionId);
}
