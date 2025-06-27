package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.dto.StaffRequest;
import com.tesa.hospitalerd.service.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        staffService.getAllStaffs();
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/all-doctors")
    public ResponseEntity<?> getAllDoctors() {
        staffService.getAllDoctors();
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/available-doctors")
    public ResponseEntity<?> getAvailableDoctors(@RequestParam String date,
                                                 @RequestParam String time) {
        staffService.getAvailableDoctors(date, time);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable int id) {
        staffService.getStaffById(id);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchStaff(@RequestParam String query) {
        staffService.searchStaff(query);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable int id, @RequestBody StaffRequest request) {
        staffService.updateStaff(id, request);
        return ResponseEntity.ok().body("");
    }
}
