package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.EquipmentInventory;

import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * CRUD + custom methods for EquipmentInventory
 */
@Repository
public interface EquipmentInventoryRepository{
    int createEquipmentInventory(EquipmentInventory equipmentInventory);

    int updateEquipmentInventory(EquipmentInventory equipmentInventory);

    int deleteEquipmentInventory(Long id);

    EquipmentInventory findEquipmentInventoryById(Long id);

    List<EquipmentInventory> findAllEquipmentInventory();

    List<EquipmentInventory> findLowStock();
}
