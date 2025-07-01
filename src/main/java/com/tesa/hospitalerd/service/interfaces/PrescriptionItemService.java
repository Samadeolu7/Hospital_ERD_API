package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.model.request.PrescriptionItemCreateRequest;
import com.tesa.hospitalerd.model.request.PrescriptionItemUpdateRequest;

import java.util.List;

public interface PrescriptionItemService {

    PrescriptionItem createPrescriptionItem(PrescriptionItemCreateRequest req);

    PrescriptionItem updatePrescriptionItem(PrescriptionItemUpdateRequest req);

    void deleteByPrescriptionId(Long prescriptionId);
    PrescriptionItem findById(Long id);
    int countByPrescriptionId(Long prescriptionId);
    List<PrescriptionItem> findByPrescriptionId(Long prescriptionId);
}
