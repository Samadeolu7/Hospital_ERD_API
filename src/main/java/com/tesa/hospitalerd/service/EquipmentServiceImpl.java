package com.tesa.hospitalerd.service;

import com.google.gson.Gson;
import com.tesa.hospitalerd.model.dto.EquipmentRequest;
import com.tesa.hospitalerd.model.entity.Equipment;
import com.tesa.hospitalerd.repository.interfaces.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipmentServiceImpl implements EquipmentService{

    private final EquipmentRepository equipmentRepository;
    Gson gson = new Gson();

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }


    @Override
    public void createEquipment(EquipmentRequest request) {
        try {
            var equipment = gson.fromJson(gson.toJson(request), Equipment.class);

            System.out.println("EQUIPMENT NAME >>");
            System.out.println(equipment.getEquipmentName());

            System.out.println("EQUIPMENT Id >>");
            System.out.println(equipment.getEquipmentId());

            equipmentRepository.createEquipment(equipment);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create equipment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Equipment> getAllEquipments() {
        try {
            return equipmentRepository.findAllEquipments();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get equipments: " + e.getMessage(), e);
        }
    }

    @Override
    public Equipment getEquipmentById(int id) {
        try {
            return equipmentRepository.findEquipmentById(id)
                    .orElseThrow(() -> new NoSuchElementException("Equipment with id: " + id + " not found"));
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get equipment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Equipment> searchEquipment(String query) {
        try {
            return equipmentRepository.equipmentSearch(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to search equipment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Equipment> getEquipmentByStatus(String status) {
        try {
            return equipmentRepository.findEquipmentByStatus(status);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get equipment status: " + e.getMessage(), e);
        }
    }

    @Override
    public void markEquipmentForMaintenance(int id) {
        try {
            equipmentRepository.markEquipmentForMaintenance(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to mark equipment for maintenance: " + e.getMessage(), e);
        }
    }

    @Override
    public void markEquipmentAsAvailable(int id) {
        try {
            equipmentRepository.markEquipmentAsAvailable(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to mark equipment as available : " + e.getMessage(), e);
        }
    }

    @Override
    public void decommissionEquipment(int id) {
        try {
            equipmentRepository.decommissionEquipment(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to decommission equipment: " + e.getMessage(), e);
        }
    }


    @Override
    public void updateEquipment(int id, EquipmentRequest request) {
        try {
            Equipment existingEquipment = equipmentRepository.findEquipmentById(id)
                    .orElseThrow(() -> new NoSuchElementException("Equipment  with id: " + id + " not found"));

            Equipment updatedEquipment = gson.fromJson(gson.toJson(request), Equipment.class);
            updatedEquipment.setEquipmentId(id);
            equipmentRepository.updateEquipment(updatedEquipment);

        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update patient: " + e.getMessage(), e);
        }
    }
}
