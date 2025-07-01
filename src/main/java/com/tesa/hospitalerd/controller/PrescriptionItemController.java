package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.PrescriptionItem;
import com.tesa.hospitalerd.model.request.PrescriptionItemCreateRequest;
import com.tesa.hospitalerd.model.request.PrescriptionItemUpdateRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.PrescriptionItemResponse;
import com.tesa.hospitalerd.service.interfaces.PrescriptionItemService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prescription-items")
public class PrescriptionItemController {

    private final PrescriptionItemService service;
    private final ModelMapper mapper;

    public PrescriptionItemController(
            PrescriptionItemService service,
            ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PrescriptionItemResponse>> create(
            @Valid @RequestBody PrescriptionItemCreateRequest req) {
        PrescriptionItem created = service.createPrescriptionItem(req);
        PrescriptionItemResponse resp = mapper.map(created, PrescriptionItemResponse.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(resp));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<PrescriptionItemResponse>> update(
            @Valid @RequestBody PrescriptionItemUpdateRequest req) {
        PrescriptionItem updated = service.updatePrescriptionItem(req);
        PrescriptionItemResponse resp = mapper.map(updated, PrescriptionItemResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionItemResponse>> getById(
            @PathVariable Long id) {
        PrescriptionItem e = service.findById(id);
        PrescriptionItemResponse resp = mapper.map(e, PrescriptionItemResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/by-prescription/{prescriptionId}")
    public ResponseEntity<ApiResponse<List<PrescriptionItemResponse>>> byPrescription(
            @PathVariable Long prescriptionId) {
        List<PrescriptionItemResponse> resp = service.findByPrescriptionId(prescriptionId)
                .stream()
                .map(pi -> mapper.map(pi, PrescriptionItemResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{prescriptionId}/count")
    public ResponseEntity<ApiResponse<Integer>> count(
            @PathVariable Long prescriptionId) {
        int cnt = service.countByPrescriptionId(prescriptionId);
        return ResponseEntity.ok(ResponseBuilder.success(cnt));
    }

    @DeleteMapping("/by-prescription/{prescriptionId}")
    public ResponseEntity<ApiResponse<Void>> deleteByPrescription(
            @PathVariable Long prescriptionId) {
        service.deleteByPrescriptionId(prescriptionId);
        return ResponseEntity.ok(ResponseBuilder.success("Deleted successfully", null));
    }
}
