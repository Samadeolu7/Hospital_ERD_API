package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.MedicationDispense;
import com.tesa.hospitalerd.model.request.MedicationDispenseCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationDispenseUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationDispenseRepository;
import com.tesa.hospitalerd.service.interfaces.MedicationDispenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationDispenseServiceImpl implements MedicationDispenseService {

    private final MedicationDispenseRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public MedicationDispenseServiceImpl(MedicationDispenseRepository repo,
                                         ModelMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    @Override
    public MedicationDispense createMedicationDispense(MedicationDispenseCreateRequest req) {
        MedicationDispense entity = mapper.map(req, MedicationDispense.class);
        repo.createMedicationDispense(entity);
        MedicationDispense saved = repo.findMedicationDispenseById(entity.getMedicationDispenseID());
        return mapper.map(saved, MedicationDispense.class);
    }

    @Override
    public MedicationDispense updateMedicationDispense(MedicationDispenseUpdateRequest req) {
        MedicationDispense entity = mapper.map(req, MedicationDispense.class);
        repo.updateMedicationDispense(entity);
        MedicationDispense updated = repo.findMedicationDispenseById(entity.getMedicationDispenseID());
        return mapper.map(updated, MedicationDispense.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteMedicationDispenseById(id);
    }

    @Override
    public MedicationDispense findById(Long id) {
        MedicationDispense e = repo.findMedicationDispenseById(id);
        return mapper.map(e, MedicationDispense.class);
    }

    @Override
    public List<MedicationDispense> findByMedicationId(Long medId) {
        return repo.findMedicationDispenseByMedicationID(medId)
                .stream()
                .map(e -> mapper.map(e, MedicationDispense.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicationDispense> findByDispenserId(Long staffId) {
        return repo.findByDispenserId(staffId)
                .stream()
                .map(e -> mapper.map(e, MedicationDispense.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicationDispense> findByDateRange(LocalDate start, LocalDate end) {
        return repo.findByDispensedAtBetween(start, end)
                .stream()
                .map(e -> mapper.map(e, MedicationDispense.class))
                .collect(Collectors.toList());
    }
}
