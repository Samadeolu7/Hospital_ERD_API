package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.dto.EquipmentRequest;
import com.tesa.hospitalerd.service.EquipmentService;
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
    public ResponseEntity<?> createStaff(@RequestBody EquipmentRequest request) {
        equipmentService.createEquipment(request);
        return ResponseEntity.ok().body("Staff created successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEquipments() {
        equipmentService.getAllEquipments();
        return ResponseEntity.ok().body("");
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEquipmentById(@PathVariable int id) {
        equipmentService.getEquipmentById(id);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEquipment(@RequestParam String query) {
        equipmentService.searchEquipment(query);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable int id, @RequestBody EquipmentRequest request) {
        equipmentService.updateEquipment(id, request);
        return ResponseEntity.ok().body("");
    }

}
