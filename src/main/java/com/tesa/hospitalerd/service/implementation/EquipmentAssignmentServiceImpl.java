package com.tesa.hospitalerd.service.implementation;

import com.tesa.hospitalerd.model.entity.EquipmentAssignment;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentUpdateRequest;
import com.tesa.hospitalerd.repository.database.interfaces.EquipmentAssignmentRepository;
import com.tesa.hospitalerd.service.interfaces.EquipmentAssignmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentAssignmentServiceImpl implements EquipmentAssignmentService {

    private final EquipmentAssignmentRepository repo;
    private final ModelMapper mapper;


    @Autowired
    public EquipmentAssignmentServiceImpl(EquipmentAssignmentRepository repo,
                                          ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public EquipmentAssignment create(EquipmentAssignmentCreateRequest req) {
        // 1) Map request → entity
        EquipmentAssignment entity = mapper.map(req, EquipmentAssignment.class);
        // 2) Persist
        repo.createEquipmentAssignment(entity);
        // 3) Reload (to get generated ID, audit fields, etc.)
        EquipmentAssignment saved = repo.findEquipmentAssignmentById(entity.getEquipmentAssignmentID().intValue());
        // 4) Map entity →  DTO
        return mapper.map(saved, EquipmentAssignment.class);
    }

    @Override
    public EquipmentAssignment update(EquipmentAssignmentUpdateRequest req) {
        EquipmentAssignment entity = mapper.map(req, EquipmentAssignment.class);
        repo.updateEquipmentAssignment(entity);
        EquipmentAssignment updated = repo.findEquipmentAssignmentById(entity.getEquipmentAssignmentID().intValue());
        return mapper.map(updated, EquipmentAssignment.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteEquipmentAssignment(id.intValue());
    }

    @Override
    public EquipmentAssignment findById(Long id) {
        EquipmentAssignment e = repo.findEquipmentAssignmentById(id.intValue());
        return mapper.map(e, EquipmentAssignment.class);
    }

    @Override
    public List<EquipmentAssignment> findAll() {
        return repo.findAllEquipmentAssignment()
                .stream()
                .map(e -> mapper.map(e, EquipmentAssignment.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentAssignment> findByStaffId(Long staffId) {
        return repo.findByStaffId(staffId)
                .stream()
                .map(e -> mapper.map(e, EquipmentAssignment.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentAssignment> findUnreturned() {
        return repo.findByReturnedAtIsNull()
                .stream()
                .map(e -> mapper.map(e, EquipmentAssignment.class))
                .collect(Collectors.toList());
    }
}
