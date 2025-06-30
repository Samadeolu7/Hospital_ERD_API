package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.request.EquipmentRequest;
import com.tesa.hospitalerd.service.interfaces.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEquipment(@RequestBody EquipmentRequest request) {
        equipmentService.createEquipment(request);
        return ResponseEntity.ok().body("Staff created successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEquipments() {
        equipmentService.getAllEquipments();
        return ResponseEntity.ok().body("");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEquipmentById(@PathVariable Long id) {
        equipmentService.getEquipmentById(id);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEquipment(@RequestParam String query) {
        equipmentService.searchEquipment(query);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> findEquipmentByStatus(@PathVariable String status) {
        equipmentService.getEquipmentByStatus(status);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{id}/maintenance")
    public ResponseEntity<?> markEquipmentForMaintenance(@PathVariable Long id) {
        equipmentService.markEquipmentForMaintenance(id);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{id}/available")
    public ResponseEntity<?> markEquipmentAsAvailable(@PathVariable Long id) {
        equipmentService.markEquipmentAsAvailable(id);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{id}/decommission")
    public ResponseEntity<?> decommissionEquipment(@PathVariable Long id) {
        equipmentService.decommissionEquipment(id);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable Long id, @RequestBody EquipmentRequest request) {
        equipmentService.updateEquipment(id, request);
        return ResponseEntity.ok().body("");
    }

}
