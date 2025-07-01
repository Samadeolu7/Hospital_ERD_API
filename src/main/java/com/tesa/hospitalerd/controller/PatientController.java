package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.request.PatientRequest;
import com.tesa.hospitalerd.model.entity.Patient;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.service.interfaces.PatientService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Patient>> createPatient(@RequestBody PatientRequest request) {
        Patient created = patientService.createPatient(request);
        return ResponseEntity.ok(ResponseBuilder.success("Patient created successfully!", created));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<java.util.List<Patient>>> getAllPatients() {
        java.util.List<Patient> patients = patientService.getAllPatients();
        String msg = patients.isEmpty() ? "No patients found" : "Patients fetched successfully";
        return ResponseEntity.ok(ResponseBuilder.success(msg, patients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        String msg = (patient != null) ? "Patient fetched successfully" : "Patient not found";
        return ResponseEntity.ok(ResponseBuilder.success(msg, patient));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<java.util.List<Patient>>> searchPatient(@RequestParam String query) {
        java.util.List<Patient> patients = patientService.searchPatient(query);
        String msg = patients.isEmpty() ? "No patients matched your search" : "Search completed";
        return ResponseEntity.ok(ResponseBuilder.success(msg, patients));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> updatePatient(@PathVariable Long id, @RequestBody PatientRequest request) {
        Patient updated = patientService.updatePatient(id, request);
        String msg = (updated != null) ? "Patient updated successfully!" : "Patient not found or not updated";
        return ResponseEntity.ok(ResponseBuilder.success(msg, updated));
    }
}
