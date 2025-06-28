package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.MedicationDispense;
import com.tesa.hospitalerd.model.request.MedicationDispenseCreateRequest;
import com.tesa.hospitalerd.model.request.MedicationDispenseUpdateRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.MedicationDispenseResponse;
import com.tesa.hospitalerd.service.interfaces.MedicationDispenseService;
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
@RequestMapping("/dispenses")
public class MedicationDispenseController {

    private final MedicationDispenseService service;
    private final ModelMapper mapper;

    public MedicationDispenseController(
            MedicationDispenseService service,
            ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MedicationDispenseResponse>> create(
            @Valid @RequestBody MedicationDispenseCreateRequest req) {
        MedicationDispense created = service.createMedicationDispense(req);
        MedicationDispenseResponse resp = mapper.map(created, MedicationDispenseResponse.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(resp));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MedicationDispenseResponse>> update(
            @Valid @RequestBody MedicationDispenseUpdateRequest req) {
        MedicationDispense updated = service.updateMedicationDispense(req);
        MedicationDispenseResponse resp = mapper.map(updated, MedicationDispenseResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicationDispenseResponse>> getById(
            @PathVariable Long id) {
        MedicationDispense e = service.findById(id);
        MedicationDispenseResponse resp = mapper.map(e, MedicationDispenseResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/by-medication/{medId}")
    public ResponseEntity<ApiResponse<List<MedicationDispenseResponse>>> byMedication(
            @PathVariable Long medId) {
        List<MedicationDispenseResponse> resp = service.findByMedicationId(medId)
                .stream()
                .map(e -> mapper.map(e, MedicationDispenseResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/by-dispenser/{staffId}")
    public ResponseEntity<ApiResponse<List<MedicationDispenseResponse>>> byDispenser(
            @PathVariable Long staffId) {
        List<MedicationDispenseResponse> resp = service.findByDispenserId(staffId)
                .stream()
                .map(e -> mapper.map(e, MedicationDispenseResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/range")
    public ResponseEntity<ApiResponse<List<MedicationDispenseResponse>>> byDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        List<MedicationDispenseResponse> resp = service.findByDateRange(from, to)
                .stream()
                .map(e -> mapper.map(e, MedicationDispenseResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success("Deleted successfully", null));
    }
}
