package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.EquipmentInventory;

import java.util.List;
import java.util.Optional;

/** Generic CRUD plus domain-specific operations for EquipmentInventory */
public interface EquipmentInventoryService {
    // Create, Read, Update, Delete
    EquipmentInventory create(EquipmentInventory equipmentInventory);
    Optional<EquipmentInventory> findById(Long id);
    List<EquipmentInventory> findAll();
    EquipmentInventory update(EquipmentInventory equipmentInventory);
    void delete(Long id);

    // Domain-specific: assign and return
    /**
     * Reserve a quantity of equipment for assignment; throws if not enough available.
     */
    void reserveEquipment(Long inventoryId, int quantity, Staff assignedTo, User recorder);

    /**
     * Return previously reserved equipment back into available pool.
     */
    void returnEquipment(Long assignmentId, User recorder);

    /**
     * List inventories below reorder level.
     */
    List<EquipmentInventory> findLowStock();
}