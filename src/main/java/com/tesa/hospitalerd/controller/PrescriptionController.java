package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.Prescription;
import com.tesa.hospitalerd.model.request.PrescriptionCreateRequest;
import com.tesa.hospitalerd.model.request.PrescriptionUpdateRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.PrescriptionResponse;
import com.tesa.hospitalerd.service.interfaces.PrescriptionService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;
    private final ModelMapper mapper;

    public PrescriptionController(
            PrescriptionService service,
            ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PrescriptionResponse>> create(
            @Valid @RequestBody PrescriptionCreateRequest req) {
        Prescription created = service.createPrescription(req);
        PrescriptionResponse resp = mapper.map(created, PrescriptionResponse.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(resp));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<PrescriptionResponse>> update(
            @Valid @RequestBody PrescriptionUpdateRequest req) {
        Prescription updated = service.updatePrescription(req);
        PrescriptionResponse resp = mapper.map(updated, PrescriptionResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> getById(
            @PathVariable Long id) {
        Prescription e = service.findById(id);
        PrescriptionResponse resp = mapper.map(e, PrescriptionResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<ApiResponse<List<PrescriptionResponse>>> byPatient(
            @PathVariable Long patientId) {
        List<PrescriptionResponse> resp = service.findByPatientId(patientId)
                .stream()
                .map(p -> mapper.map(p, PrescriptionResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<ApiResponse<List<PrescriptionResponse>>> byDoctor(
            @PathVariable Long doctorId) {
        List<PrescriptionResponse> resp = service.findByDoctorId(doctorId)
                .stream()
                .map(p -> mapper.map(p, PrescriptionResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/expired-unfulfilled")
    public ResponseEntity<ApiResponse<List<PrescriptionResponse>>> expiredUnfulfilled() {
        List<PrescriptionResponse> resp = service.findExpiredUnfulfilled()
                .stream()
                .map(p -> mapper.map(p, PrescriptionResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.success("Deleted successfully", null));
    }
}
