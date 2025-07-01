package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.EquipmentInventory;
import com.tesa.hospitalerd.model.request.EquipmentInventoryCreateRequest;
import com.tesa.hospitalerd.model.request.EquipmentInventoryUpdateRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.model.response.EquipmentInventoryResponse;
import com.tesa.hospitalerd.service.interfaces.EquipmentInventoryService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/equipment-inventory")
public class EquipmentInventoryController {

    private final EquipmentInventoryService service;
    private final ModelMapper mapper;

    public EquipmentInventoryController(EquipmentInventoryService service,
                                        ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EquipmentInventoryResponse>> create(
            @Valid @RequestBody EquipmentInventoryCreateRequest req) {
        EquipmentInventory created = service.createEquipmentInventory(req);
        EquipmentInventoryResponse resp = mapper.map(created, EquipmentInventoryResponse.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(resp));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<EquipmentInventoryResponse>> update(
            @Valid @RequestBody EquipmentInventoryUpdateRequest req) {
        EquipmentInventory updated = service.updateEquipmentInventory(req);
        EquipmentInventoryResponse resp = mapper.map(updated, EquipmentInventoryResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipmentInventoryResponse>> getById(
            @PathVariable Long id) {
        EquipmentInventory e = service.findEquipmentInventoryById(id);
        EquipmentInventoryResponse resp = mapper.map(e, EquipmentInventoryResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EquipmentInventoryResponse>>> listAll() {
        List<EquipmentInventoryResponse> resp = service.findAllEquipmentInventory()
                .stream()
                .map(e -> mapper.map(e, EquipmentInventoryResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<EquipmentInventoryResponse>>> lowStock() {
        List<EquipmentInventoryResponse> resp = service.findLowStock()
                .stream()
                .map(e -> mapper.map(e, EquipmentInventoryResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseBuilder.success(resp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.deleteEquipmentInventory(id);
        return ResponseEntity.ok(ResponseBuilder.success("Deleted successfully", null));
    }
}
