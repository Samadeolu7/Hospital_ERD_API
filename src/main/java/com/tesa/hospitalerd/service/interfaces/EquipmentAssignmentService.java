package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.request.EquipmentAssignmentCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentUpdateRequest;
import com.tesa.hospitalerd.model.response.EquipmentAssignmentResponse;

import java.util.List;

public interface EquipmentAssignmentService {
    EquipmentAssignmentResponse create(EquipmentAssignmentCreateRequest req);
    EquipmentAssignmentResponse update(EquipmentAssignmentUpdateRequest req);
    void delete(Long id);
    EquipmentAssignmentResponse findById(Long id);
    List<EquipmentAssignmentResponse> findAll();
    List<EquipmentAssignmentResponse> findByStaffId(Long staffId);
    List<EquipmentAssignmentResponse> findUnreturned();
}
