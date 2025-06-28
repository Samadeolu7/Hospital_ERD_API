package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.model.request.MedicationCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationRepository;
import com.tesa.hospitalerd.service.interfaces.MedicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public MedicationServiceImpl(MedicationRepository repo,
                                 ModelMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
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
}
