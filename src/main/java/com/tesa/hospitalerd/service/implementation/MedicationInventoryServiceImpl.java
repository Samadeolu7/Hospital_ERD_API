package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.model.request.MedicationInventoryCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationInventoryUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationInventoryRepository;
import com.tesa.hospitalerd.service.interfaces.MedicationInventoryService;
import com.tesa.hospitalerd.service.interfaces.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationInventoryServiceImpl implements MedicationInventoryService {

    private final MedicationInventoryRepository repo;
    private final ModelMapper mapper;
    private final MedicationService medicationService;

    @Autowired
    public MedicationInventoryServiceImpl(MedicationInventoryRepository repo,
                                          ModelMapper mapper,
                                          MedicationService medicationService) {
        this.repo   = repo;
        this.mapper = mapper;
        this.medicationService = medicationService;
    }

    @Override
    public MedicationInventory createMedicationInventory(MedicationInventoryCreateRequest req) {
        MedicationInventory entity = mapper.map(req, MedicationInventory.class);
        repo.createMedicationInventory(entity);
        MedicationInventory saved = repo.findMedicationInventoryById(entity.getMedicationInventoryID());
        // Recalculate medication quantities after new inventory is created
        medicationService.recalculateQuantities(saved.getMedicationID());
        return mapper.map(saved, MedicationInventory.class);
    }

    @Override
    public MedicationInventory updateMedicationInventory(MedicationInventoryUpdateRequest req) {
        MedicationInventory entity = mapper.map(req, MedicationInventory.class);
        repo.updateMedicationInventory(entity);
        MedicationInventory updated = repo.findMedicationInventoryById(entity.getMedicationInventoryID());
        return mapper.map(updated, MedicationInventory.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteMedicationInventoryById(id);
    }

    @Override
    public MedicationInventory findById(Long id) {
        MedicationInventory e = repo.findMedicationInventoryById(id);
        return mapper.map(e, MedicationInventory.class);
    }

    @Override
    public List<MedicationInventory> findByMedicationId(Long medId) {
        return repo.findMedicationInventoryByMedicationID(medId)
                .stream()
                .map(e -> mapper.map(e, MedicationInventory.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicationInventory> findExpiringBefore(LocalDate date) {
        return repo.findByExpiryDateBefore(date)
                .stream()
                .map(e -> mapper.map(e, MedicationInventory.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicationInventory> findAll() {
        return repo.findAllMedicationInventory()
                .stream()
                .map(e -> mapper.map(e, MedicationInventory.class))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalAvailableQuantity(Long medicationId) {
        return repo.sumAvailableQuantityByMedicationId(medicationId);
    }

    @Override
    public int getTotalQuantity(Long medicationId) {
        return repo.sumTotalQuantityByMedicationId(medicationId);
    }
}
