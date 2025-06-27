package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository {

    void createEquipment(Equipment equipment);
    List<Equipment> findAllEquipments();
    Optional<Equipment> findEquipmentById(int id);
    List<Equipment> equipmentSearch(String query);
    List<Equipment> findEquipmentByStatus(String status);
    void markEquipmentForMaintenance(int id);
    void markEquipmentAsAvailable(int id);
    void decommissionEquipment(int id);
    void updateEquipment(Equipment equipment);
}
