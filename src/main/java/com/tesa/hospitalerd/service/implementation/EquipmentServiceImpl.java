package com.tesa.hospitalerd.service.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tesa.hospitalerd.model.request.EquipmentRequest;
import com.tesa.hospitalerd.model.entity.Equipment;
import com.tesa.hospitalerd.repository.database.interfaces.EquipmentRepository;
import com.tesa.hospitalerd.service.interfaces.EquipmentService;
import com.tesa.hospitalerd.util.LocalDateTimeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }


    @Override
    public Equipment createEquipment(EquipmentRequest request) {
        try {
            var equipment = gson.fromJson(gson.toJson(request), Equipment.class);

            System.out.println("EQUIPMENT NAME >");
            System.out.println(equipment.getEquipmentName());

            System.out.println("EQUIPMENT Id >");
            System.out.println(equipment.getEquipmentId());

            equipmentRepository.createEquipment(equipment);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create equipment: " + e.getMessage(), e);
        }
        return null;
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
    public Equipment getEquipmentById(Long id) {
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
    public Equipment markEquipmentForMaintenance(Long id) {
        try {
            equipmentRepository.markEquipmentForMaintenance(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to mark equipment for maintenance: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Equipment markEquipmentAsAvailable(Long id) {
        try {
            equipmentRepository.markEquipmentAsAvailable(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to mark equipment as available : " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Equipment decommissionEquipment(Long id) {
        try {
            equipmentRepository.decommissionEquipment(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to decommission equipment: " + e.getMessage(), e);
        }
        return null;
    }


    @Override
    public Equipment updateEquipment(Long id, EquipmentRequest request) {
        try {
            Equipment existingEquipment = equipmentRepository.findEquipmentById(id)
                    .orElseThrow(() -> new NoSuchElementException("Equipment  with id: " + id + " not found"));

            Equipment updatedEquipment = gson.fromJson(gson.toJson(request), Equipment.class);
            updatedEquipment.setEquipmentId(id);
            equipmentRepository.updateEquipment(updatedEquipment);

        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update patient: " + e.getMessage(), e);
        }
        return null;
    }
}
