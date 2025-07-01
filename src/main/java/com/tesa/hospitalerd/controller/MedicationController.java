package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.Medication;
import com.tesa.hospitalerd.model.request.MedicationCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationUpdateRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.MedicationResponse;
import com.tesa.hospitalerd.model.response.PrescriptionItemResponse;
import com.tesa.hospitalerd.service.interfaces.MedicationService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService service;
    private final ModelMapper mapper;

    public MedicationController(MedicationService service,
                                ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MedicationResponse>> create(
            @Valid @RequestBody MedicationCreateRequest req) {
        Medication created = service.createMedication(req);
        MedicationResponse resp = mapper.map(created, MedicationResponse.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(resp));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MedicationResponse>> update(
            @Valid @RequestBody MedicationUpdateRequest req) {
        Medication updated = service.updateMedication(req);
        MedicationResponse resp = mapper.map(updated, MedicationResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicationResponse>> getById(
            @PathVariable Long id) {
        Medication e = service.findById(id);
        MedicationResponse resp = mapper.map(e, MedicationResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/all-medications")
    public ResponseEntity<ApiResponse<List<MedicationResponse>>> allMedications() {
        List<MedicationResponse> resp = service.findAll()
                .stream()
                .map(m -> mapper.map(m, MedicationResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/requires-rx")
    public ResponseEntity<ApiResponse<List<MedicationResponse>>> listRx() {
        List<MedicationResponse> resp = service.findRequiresRx()
                .stream()
                .map(m -> mapper.map(m, MedicationResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<ApiResponse<List<PrescriptionItemResponse>>> items(
            @PathVariable Long id) {
        List<PrescriptionItemResponse> resp = service.findPrescriptionItems(id)
                .stream()
                .map(pi -> mapper.map(pi, PrescriptionItemResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<MedicationResponse>>> lowStock() {
        List<MedicationResponse> resp = service.findLowStock()
                .stream()
                .map(med->mapper.map(med,MedicationResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success("Deleted successfully", null));
    }
}
