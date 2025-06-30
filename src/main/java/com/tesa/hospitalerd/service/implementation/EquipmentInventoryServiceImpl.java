package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.EquipmentInventory;
import com.tesa.hospitalerd.model.request.EquipmentInventoryCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentInventoryUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.EquipmentInventoryRepository;
import com.tesa.hospitalerd.service.interfaces.EquipmentInventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentInventoryServiceImpl implements EquipmentInventoryService {

    private final EquipmentInventoryRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public EquipmentInventoryServiceImpl(EquipmentInventoryRepository repo,
                                         ModelMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }


    public EquipmentInventory createEquipmentInventory(EquipmentInventoryCreateRequest req) {
        EquipmentInventory entity = mapper.map(req, EquipmentInventory.class);
        repo.createEquipmentInventory(entity);
        EquipmentInventory saved = repo.findEquipmentInventoryById(entity.getEquipmentInventoryID());
        return mapper.map(saved, EquipmentInventory.class);
    }

    @Override
    public EquipmentInventory updateEquipmentInventory(EquipmentInventoryUpdateRequest req) {
        EquipmentInventory entity = mapper.map(req, EquipmentInventory.class);
        repo.updateEquipmentInventory(entity);
        EquipmentInventory updated = repo.findEquipmentInventoryById(entity.getEquipmentInventoryID());
        return mapper.map(updated, EquipmentInventory.class);
    }

    @Override
    public void deleteEquipmentInventory(Long id) {
        repo.deleteEquipmentInventory(id);
    }

    @Override
    public EquipmentInventory findEquipmentInventoryById(Long id) {
        EquipmentInventory e = repo.findEquipmentInventoryById(id);
        return mapper.map(e, EquipmentInventory.class);
    }

    @Override
    public List<EquipmentInventory> findAllEquipmentInventory() {
        return repo.findAllEquipmentInventory()
                .stream()
                .map(e -> mapper.map(e, EquipmentInventory.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentInventory> findLowStock() {
        return repo.findLowStock()
                .stream()
                .map(e -> mapper.map(e, EquipmentInventory.class))
                .collect(Collectors.toList());
    }
}
