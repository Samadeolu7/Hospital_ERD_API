package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.request.PatientRequest;
import com.tesa.hospitalerd.service.interfaces.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@RequestBody PatientRequest request) {
        patientService.createPatient(request);
        return ResponseEntity.ok().body("Patient created successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPatients() {
        patientService.getAllPatients();
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable int id) {
        patientService.getPatientById(id);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPatient(@RequestParam String query) {
        patientService.searchPatient(query);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable int id, @RequestBody PatientRequest request) {
        patientService.updatePatient(id, request);
        return ResponseEntity.ok().body("");
    }
}
