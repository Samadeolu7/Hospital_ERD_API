package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.EquipmentInventory;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentInventoryCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentInventoryUpdateRequest;

import java.util.List;

public interface EquipmentInventoryService {
    EquipmentInventory createEquipmentInventory(EquipmentInventoryCreateRequest req);
    EquipmentInventory updateEquipmentInventory(EquipmentInventoryUpdateRequest request);
    void deleteEquipmentInventory(Long id);
    EquipmentInventory findEquipmentInventoryById(Long id);
    List<EquipmentInventory> findAllEquipmentInventory();
    List<EquipmentInventory> findLowStock(int reorderLevel);
}