package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.EquipmentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRUD + custom methods for EquipmentAssignment
 */
@Repository
public interface EquipmentAssignmentRepository{
    int createEquipmentAssignment(EquipmentAssignment equipmentAssignment);

    int updateEquipmentAssignment(EquipmentAssignment equipmentAssignment);

    int deleteEquipmentAssignment(Integer id);

    EquipmentAssignment findEquipmentAssignmentById(Integer id);

    List<EquipmentAssignment> findAllEquipmentAssignment();

    List<EquipmentAssignment> findByStaffId(Long staffId);

    List<EquipmentAssignment> findByReturnedAtIsNull();
}
