package com.tesa.hospitalerd.service;

import com.google.gson.Gson;
import com.tesa.hospitalerd.model.dto.StaffRequest;
import com.tesa.hospitalerd.model.entity.Staff;
import com.tesa.hospitalerd.repository.interfaces.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StaffServiceImpl implements StaffService{

    private final StaffRepository staffRepository;
    Gson gson = new Gson();

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public void createStaff(StaffRequest request) {
        try {
            var staff = gson.fromJson(gson.toJson(request), Staff.class);

            System.out.println("PATIENT NAME >>");
            System.out.println(staff.getStaffFirstName());

            System.out.println("PATIENT Id >>");
            System.out.println(staff.getStaffId());

            staffRepository.createStaff(staff);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create staff: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Staff> getAllStaffs() {
        try {
            return staffRepository.findAllStaffs();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get staffs: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Staff> getAllDoctors() {
        try {
            return staffRepository.findAllDoctors();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get doctors: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Staff> getAvailableDoctors(String date, String time) {
        try {
            return staffRepository.findAvailableDoctors(date, time);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get available doctors: " + e.getMessage(), e);
        }
    }

    @Override
    public Staff getStaffById(int id) {
        try {
            return staffRepository.findStaffById(id)
                    .orElseThrow(() -> new NoSuchElementException("Staff with id: " + id + " not found"));
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get staff: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Staff> searchStaff(String query) {
        try {
            return staffRepository.staffSearch(query);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to search staff: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateStaff(int id, StaffRequest request) {
        try {
            Staff existingStaff = staffRepository.findStaffById(id)
                    .orElseThrow(() -> new NoSuchElementException("Staff  with id: " + id + " not found"));

            Staff updatedStaff = gson.fromJson(gson.toJson(request), Staff.class);
            updatedStaff.setStaffId(id);
            staffRepository.updateStaff(updatedStaff);

        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update staff: " + e.getMessage(), e);
        }
    }
}
