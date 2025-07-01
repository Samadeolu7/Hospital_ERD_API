package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.EquipmentAssignment;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentAssignmentUpdateRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.EquipmentAssignmentResponse;
import com.tesa.hospitalerd.service.interfaces.EquipmentAssignmentService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/equipment-assignments")
public class EquipmentAssignmentController {

    private final EquipmentAssignmentService service;
    private final ModelMapper mapper;

    public EquipmentAssignmentController(EquipmentAssignmentService service,
                                         ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EquipmentAssignmentResponse>> create(
            @Valid @RequestBody EquipmentAssignmentCreateRequest req
    ) {
        EquipmentAssignment created = service.create(req);
        EquipmentAssignmentResponse resp = mapper.map(created, EquipmentAssignmentResponse.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(resp));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<EquipmentAssignmentResponse>> update(
            @Valid @RequestBody EquipmentAssignmentUpdateRequest req
    ) {
        EquipmentAssignment updated = service.update(req);
        EquipmentAssignmentResponse resp = mapper.map(updated, EquipmentAssignmentResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipmentAssignmentResponse>> getById(
            @PathVariable Long id
    ) {
        EquipmentAssignment e = service.findById(id);
        EquipmentAssignmentResponse resp = mapper.map(e, EquipmentAssignmentResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EquipmentAssignmentResponse>>> listAll() {
        List<EquipmentAssignmentResponse> resp = service.findAll()
                .stream()
                .map(e -> mapper.map(e, EquipmentAssignmentResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/by-staff/{staffId}")
    public ResponseEntity<ApiResponse<List<EquipmentAssignmentResponse>>> listByStaff(
            @PathVariable Long staffId
    ) {
        List<EquipmentAssignmentResponse> resp = service.findByStaffId(staffId)
                .stream()
                .map(e -> mapper.map(e, EquipmentAssignmentResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/unreturned")
    public ResponseEntity<ApiResponse<List<EquipmentAssignmentResponse>>> listUnreturned() {
        List<EquipmentAssignmentResponse> resp = service.findUnreturned()
                .stream()
                .map(e -> mapper.map(e, EquipmentAssignmentResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success("Deleted successfully", null));
    }
}
