package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.EquipmentAssignment;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentUpdateRequest;

import java.util.List;

public interface EquipmentAssignmentService {
    EquipmentAssignment create(EquipmentAssignmentCreateRequest req);
    EquipmentAssignment update(EquipmentAssignmentUpdateRequest req);
    void delete(Long id);
    EquipmentAssignment findById(Long id);
    List<EquipmentAssignment> findAll();
    List<EquipmentAssignment> findByStaffId(Long staffId);
    List<EquipmentAssignment> findUnreturned();
}
