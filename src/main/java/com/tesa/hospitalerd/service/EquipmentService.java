package com.tesa.hospitalerd.service;

import com.tesa.hospitalerd.model.dto.EquipmentRequest;
import com.tesa.hospitalerd.model.entity.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {

    void createEquipment(EquipmentRequest request);
    List<Equipment> getAllEquipments();
    Equipment getEquipmentById(int id);
    List<Equipment> searchEquipment(String query);
    List<Equipment> findEquipmentByStatus(String status);
    void markEquipmentForMaintenance(int id);
    void markEquipmentAsAvailable(int id);
    void decommissionEquipment(int id);
    void updateEquipment(int id, EquipmentRequest request);
}
