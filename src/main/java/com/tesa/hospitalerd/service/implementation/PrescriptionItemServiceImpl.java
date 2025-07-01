package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.model.request.PrescriptionItemCreateRequest;
import com.tesa.hospitalerd.model.request.PrescriptionItemUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.PrescriptionItemRepository;
import com.tesa.hospitalerd.service.interfaces.PrescriptionItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionItemServiceImpl implements PrescriptionItemService {

    private final PrescriptionItemRepository repo;
    private final ModelMapper mapper;

    @Autowired
    public PrescriptionItemServiceImpl(PrescriptionItemRepository repo,
                                       ModelMapper mapper) {
        this.repo   = repo;
        this.mapper = mapper;
    }

    @Override
    public PrescriptionItem createPrescriptionItem(PrescriptionItemCreateRequest req) {
        PrescriptionItem entity = mapper.map(req, PrescriptionItem.class);
        repo.createPrescriptionItem(entity);
        PrescriptionItem saved = repo.findPrescriptionItemById(entity.getPrescriptionItemID());
        return mapper.map(saved, PrescriptionItem.class);
    }

    @Override
    public PrescriptionItem updatePrescriptionItem(PrescriptionItemUpdateRequest req) {
        PrescriptionItem entity = mapper.map(req, PrescriptionItem.class);
        repo.updatePrescriptionItem(entity);
        PrescriptionItem updated = repo.findPrescriptionItemById(entity.getPrescriptionItemID());
        return mapper.map(updated, PrescriptionItem.class);
    }

    @Override
    public void deleteByPrescriptionId(Long prescriptionId) {
        repo.deleteByPrescriptionId(prescriptionId);
    }

    @Override
    public PrescriptionItem findById(Long id) {
        PrescriptionItem e = repo.findPrescriptionItemById(id);
        return mapper.map(e, PrescriptionItem.class);
    }

    @Override
    public int countByPrescriptionId(Long prescriptionId) {
        return repo.countByPrescriptionId(prescriptionId);
    }

    @Override
    public List<PrescriptionItem> findByPrescriptionId(Long prescriptionId) {
        return repo.findByPrescriptionId(prescriptionId)
                .stream()
                .map(pi -> mapper.map(pi, PrescriptionItem.class))
                .collect(Collectors.toList());
    }
}
