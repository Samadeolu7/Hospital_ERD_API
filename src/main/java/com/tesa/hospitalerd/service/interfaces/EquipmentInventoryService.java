package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.EquipmentInventory;

import java.util.List;

public interface EquipmentInventoryService {
    EquipmentInventory create(EquipmentInventory inventory);
    EquipmentInventory update(EquipmentInventory inventory);
    void delete(Long id);
    EquipmentInventory findById(Long id);
    List<EquipmentInventory> findAll();
    List<EquipmentInventory> findLowStock(int reorderLevel);
}