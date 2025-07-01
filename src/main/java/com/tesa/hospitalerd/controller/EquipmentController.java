package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.Equipment;
import com.tesa.hospitalerd.model.request.EquipmentRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.service.interfaces.EquipmentService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Equipment>> createEquipment(@RequestBody EquipmentRequest request) {
        Equipment created = equipmentService.createEquipment(request);
        return ResponseEntity.ok(ResponseBuilder.success("Equipment created successfully!", created));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<java.util.List<Equipment>>> getAllEquipments() {
        List<Equipment> equipment = equipmentService.getAllEquipments();
        return ResponseEntity.ok(ResponseBuilder.success("success", equipment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Equipment>> getEquipmentById(@PathVariable Long id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        return ResponseEntity.ok(ResponseBuilder.success(equipment));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<java.util.List<Equipment>>> searchEquipment(@RequestParam String query) {
        java.util.List<Equipment> equipment = equipmentService.searchEquipment(query);
        return ResponseEntity.ok(ResponseBuilder.success(equipment));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<java.util.List<Equipment>>> findEquipmentByStatus(@PathVariable String status) {
        List<Equipment> equipment = equipmentService.getEquipmentByStatus(status);
        return ResponseEntity.ok(ResponseBuilder.success(equipment));
    }

    @PutMapping("/{id}/maintenance")
    public ResponseEntity<ApiResponse<Equipment>> markEquipmentForMaintenance(@PathVariable Long id) {
        Equipment updated = equipmentService.markEquipmentForMaintenance(id);
        return ResponseEntity.ok(ResponseBuilder.success("Equipment marked for maintenance", updated));
    }

    @PutMapping("/{id}/available")
    public ResponseEntity<ApiResponse<Equipment>> markEquipmentAsAvailable(@PathVariable Long id) {
        Equipment updated = equipmentService.markEquipmentAsAvailable(id);
        return ResponseEntity.ok(ResponseBuilder.success("Equipment marked as available", updated));
    }

    @PutMapping("/{id}/decommission")
    public ResponseEntity<ApiResponse<Equipment>> decommissionEquipment(@PathVariable Long id) {
        Equipment updated = equipmentService.decommissionEquipment(id);
        return ResponseEntity.ok(ResponseBuilder.success("Equipment decommissioned", updated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Equipment>> updateEquipment(@PathVariable Long id, @RequestBody EquipmentRequest request) {
        Equipment updated = equipmentService.updateEquipment(id, request);
        return ResponseEntity.ok(ResponseBuilder.success("Equipment updated successfully!", updated));
    }
}
