package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.Prescription;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.model.request.PrescriptionCreateRequest;
import com.tesa.hospitalerd.model.request.PrescriptionUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.PrescriptionRepository;
import com.tesa.hospitalerd.service.interfaces.MedicationService;
import com.tesa.hospitalerd.service.interfaces.PrescriptionItemService;
import com.tesa.hospitalerd.service.interfaces.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repo;
    private final ModelMapper mapper;
    private final MedicationService medicationService;
    private final PrescriptionItemService prescriptionItemService;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository repo,
                                   ModelMapper mapper,
                                   MedicationService medicationService,
                                   PrescriptionItemService prescriptionItemService) {
        this.repo   = repo;
        this.mapper = mapper;
        this.medicationService = medicationService;
        this.prescriptionItemService = prescriptionItemService;
    }

    @Override
    public Prescription createPrescription(PrescriptionCreateRequest req) {
        Prescription entity = mapper.map(req, Prescription.class);
        repo.createPrescription(entity);
        Prescription saved = repo.findPrescriptionByPrescriptionID(entity.getPrescriptionID());
        // Reduce available quantity for each item in the prescription
        List<PrescriptionItem> items = prescriptionItemService.findByPrescriptionId(saved.getPrescriptionID());
        for (PrescriptionItem item : items) {
            medicationService.adjustAvailableQuantity(item.getMedicationID(), -item.getPrescriptionItemQuantity());
        }
        return mapper.map(saved, Prescription.class);
    }

    @Override
    public Prescription updatePrescription(PrescriptionUpdateRequest req) {
        Prescription entity = mapper.map(req, Prescription.class);
        repo.updatePrescription(entity);
        Prescription updated = repo.findPrescriptionByPrescriptionID(entity.getPrescriptionID());
        return mapper.map(updated, Prescription.class);
    }

    @Override
    public void delete(Long id) {
        // Increase available quantity for each item in the prescription
        List<PrescriptionItem> items = prescriptionItemService.findByPrescriptionId(id);
        for (PrescriptionItem item : items) {
            medicationService.adjustAvailableQuantity(item.getMedicationID(), item.getPrescriptionItemQuantity());
        }
        repo.deletePrescriptionByPrescriptionID(id);
    }

    @Override
    public Prescription findById(Long id) {
        Prescription e = repo.findPrescriptionByPrescriptionID(id);
        return mapper.map(e, Prescription.class);
    }

    @Override
    public List<Prescription> findByPatientId(Long patientId) {
        return repo.findByPatientId(patientId)
                .stream()
                .map(p -> mapper.map(p, Prescription.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prescription> findByDoctorId(Long doctorId) {
        return repo.findByDoctorId(doctorId)
                .stream()
                .map(p -> mapper.map(p, Prescription.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Prescription> findExpiredUnfulfilled() {
        return repo.findExpiredUnfulfilled()
                .stream()
                .map(p -> mapper.map(p, Prescription.class))
                .collect(Collectors.toList());
    }
}
