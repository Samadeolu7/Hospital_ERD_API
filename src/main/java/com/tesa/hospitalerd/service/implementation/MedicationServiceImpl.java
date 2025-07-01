package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.model.request.MedicationCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationRepository;
import com.tesa.hospitalerd.service.interfaces.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository repo;
    private final ModelMapper mapper;
    private final com.tesa.hospitalerd.service.interfaces.MedicationInventoryService medicationInventoryService;

    @Autowired
    public MedicationServiceImpl(MedicationRepository repo,
                                 ModelMapper mapper,
                                 com.tesa.hospitalerd.service.interfaces.MedicationInventoryService medicationInventoryService) {
        this.repo   = repo;
        this.mapper = mapper;
        this.medicationInventoryService = medicationInventoryService;
    }

    @EventListener
    public void handleMedicationInventoryChangedEvent(MedicationInventoryChangedEvent event) {
        recalculateQuantities(event.getMedicationId());
    }

    @Override
    public Medication createMedication(MedicationCreateRequest req) {
        Medication entity = mapper.map(req, Medication.class);
        repo.createMedication(entity);
        Medication saved = repo.findMedicationById(entity.getMedicationID());
        return mapper.map(saved, Medication.class);
    }

    @Override
    public Medication updateMedication(MedicationUpdateRequest req) {
        Medication entity = mapper.map(req, Medication.class);
        repo.updateMedication(entity);
        Medication updated = repo.findMedicationById(entity.getMedicationID());
        return mapper.map(updated, Medication.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteMedication(id);
    }

    @Override
    public Medication findById(Long id) {
        Medication e = repo.findMedicationById(id);
        return mapper.map(e, Medication.class);
    }

    @Override
    public List<Medication> findAll() {
        return repo.findAllMedication()
                .stream()
                .map(m -> mapper.map(m, Medication.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionItem> findPrescriptionItems(Long medId) {
        return repo.findPrescriptionItemByMedicationId(medId)
                .stream()
                .map(pi -> mapper.map(pi, PrescriptionItem.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Medication> findRequiresRx() {
        return repo.findByRequiresRxTrue()
                .stream()
                .map(m -> mapper.map(m, Medication.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Medication> findLowStock() {
        return repo.findLowStock()
                .stream()
                .map(m -> mapper.map(m, Medication.class))
                .collect(Collectors.toList());
    }

    /**
     * Recalculate and update the medication's available and total quantity fields
     * using efficient SQL SUM queries.
     */
    public void recalculateQuantities(Long medicationId) {
        int available = medicationInventoryService.getTotalAvailableQuantity(medicationId);
        int total = medicationInventoryService.getTotalQuantity(medicationId);
        Medication med = repo.findMedicationById(medicationId);
        med.setMedicationAvailableQuantity(available);
        med.setMedicationTotalQuantity(total);
        repo.updateMedication(med);
    }

    /**
     * Adjust the available quantity for a medication and update the medication entity only.
     */
    public void adjustAvailableQuantity(Long medicationId, int delta) {
        Medication med = repo.findMedicationById(medicationId);
        int newAvailable = (med.getMedicationAvailableQuantity() != null ? med.getMedicationAvailableQuantity() : 0) + delta;
        med.setMedicationAvailableQuantity(Math.max(newAvailable, 0));
        repo.updateMedication(med);
    }

    /**
     * Adjust the total quantity for a medication and update the medication entity only.
     */
    public void adjustTotalQuantity(Long medicationId, int delta) {
        Medication med = repo.findMedicationById(medicationId);
        int newTotal = (med.getMedicationTotalQuantity() != null ? med.getMedicationTotalQuantity() : 0) + delta;
        med.setMedicationTotalQuantity(Math.max(newTotal, 0));
        repo.updateMedication(med);
    }
}
