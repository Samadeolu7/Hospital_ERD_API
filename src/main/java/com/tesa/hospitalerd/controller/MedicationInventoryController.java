package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.model.request.MedicationInventoryCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationInventoryUpdateRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.MedicationInventoryResponse;
import com.tesa.hospitalerd.service.interfaces.MedicationInventoryService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/med-inventory")
public class MedicationInventoryController {

    private final MedicationInventoryService service;
    private final ModelMapper mapper;

    public MedicationInventoryController(
            MedicationInventoryService service,
            ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MedicationInventoryResponse>> create(
            @Valid @RequestBody MedicationInventoryCreateRequest req) {
        MedicationInventory created = service.createMedicationInventory(req);
        MedicationInventoryResponse resp = mapper.map(created, MedicationInventoryResponse.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(resp));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MedicationInventoryResponse>> update(
            @Valid @RequestBody MedicationInventoryUpdateRequest req) {
        MedicationInventory updated = service.updateMedicationInventory(req);
        MedicationInventoryResponse resp = mapper.map(updated, MedicationInventoryResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicationInventoryResponse>> getById(
            @PathVariable Long id) {
        MedicationInventory e = service.findById(id);
        MedicationInventoryResponse resp = mapper.map(e, MedicationInventoryResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/by-medication/{medId}")
    public ResponseEntity<ApiResponse<List<MedicationInventoryResponse>>> byMedication(
            @PathVariable Long medId) {
        List<MedicationInventoryResponse> resp = service.findByMedicationId(medId)
                .stream()
                .map(e -> mapper.map(e, MedicationInventoryResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/expiring-before")
    public ResponseEntity<ApiResponse<List<MedicationInventoryResponse>>> expiringBefore(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<MedicationInventoryResponse> resp = service.findExpiringBefore(date)
                .stream()
                .map(e -> mapper.map(e, MedicationInventoryResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/all-med-inventory")
    public ResponseEntity<ApiResponse<List<MedicationInventoryResponse>>> allMedicationInventory() {
        List<MedicationInventoryResponse> resp = service.findAll()
                .stream()
                .map(e -> mapper.map(e, MedicationInventoryResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success("Deleted successfully", null));
    }
}
