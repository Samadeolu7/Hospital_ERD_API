package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.Staff;
import com.tesa.hospitalerd.model.request.StaffRequest;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.service.interfaces.StaffService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Staff>> createStaff(@RequestBody StaffRequest request) {
        Staff created = staffService.createStaff(request);
        return ResponseEntity.ok(ResponseBuilder.success("Staff created successfully!", created));
    }

    @GetMapping("/all-staffs")
    public ResponseEntity<ApiResponse<List<Staff>>> getAllStaffs() {
        List<Staff> staff = staffService.getAllStaffs();
        return ResponseEntity.ok(ResponseBuilder.success(staff));
    }

    @GetMapping("/all-doctors")
    public ResponseEntity<ApiResponse<List<Staff>>> getAllDoctors() {
        List<Staff> staff = staffService.getAllDoctors();
        return ResponseEntity.ok(ResponseBuilder.success(staff));
    }

    @GetMapping("/available-doctors")
    public ResponseEntity<ApiResponse<List<Staff>>> getAvailableDoctors(@RequestParam LocalDateTime dateTime) {
        List<Staff> staff = staffService.getAvailableDoctors(dateTime);
        return ResponseEntity.ok(ResponseBuilder.success(staff));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Staff>> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id);
        return ResponseEntity.ok(ResponseBuilder.success(staff));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Staff>>> searchStaff(@RequestParam String query) {
        List<Staff> staff = staffService.searchStaff(query);
        return ResponseEntity.ok(ResponseBuilder.success(staff));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Staff>> updatePatient(@PathVariable Long id, @RequestBody StaffRequest request) {
        Staff updated = staffService.updateStaff(id, request);
        return ResponseEntity.ok(ResponseBuilder.success("Staff updated successfully!", updated));
    }
}
