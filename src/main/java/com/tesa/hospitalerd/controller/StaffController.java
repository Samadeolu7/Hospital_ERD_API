package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.Staff;
import com.tesa.hospitalerd.model.request.StaffRequest;
import com.tesa.hospitalerd.service.interfaces.StaffService;
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
    public ResponseEntity<?> createStaff(@RequestBody StaffRequest request) {
        staffService.createStaff(request);
        return ResponseEntity.ok().body("Staff created successfully!");
    }


    @GetMapping("/all-staffs")
    public ResponseEntity<?> getAllStaffs() {
        List<Staff> staff = staffService.getAllStaffs();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/all-doctors")
    public ResponseEntity<?> getAllDoctors() {
        List <Staff> staff = staffService.getAllDoctors();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/available-doctors")
    public ResponseEntity<?> getAvailableDoctors(@RequestParam LocalDateTime dateTime) {
        List<Staff> staff = staffService.getAvailableDoctors(dateTime);
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id);
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchStaff(@RequestParam String query) {
        List<Staff> staff = staffService.searchStaff(query);
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody StaffRequest request) {
        staffService.updateStaff(id, request);
        return ResponseEntity.ok().body("");
    }
}
