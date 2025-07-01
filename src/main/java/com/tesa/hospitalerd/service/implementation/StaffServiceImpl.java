package com.tesa.hospitalerd.service.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tesa.hospitalerd.model.entity.Staff;
import com.tesa.hospitalerd.model.request.StaffRequest;
import com.tesa.hospitalerd.repository.database.interfaces.StaffRepository;
import com.tesa.hospitalerd.service.interfaces.StaffService;
import com.tesa.hospitalerd.util.LocalDateTimeAdapter;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    @Override
    public Staff createStaff(StaffRequest request) {
        try {
            var staff = gson.fromJson(gson.toJson(request), Staff.class);

            System.out.println("STAFF NAME >>");
            System.out.println(staff.getStaffFirstName());

            System.out.println("STAFF Id >>");
            System.out.println(staff.getStaffId());

            staffRepository.createStaff(staff);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create staff: " + e.getMessage(), e);
        }
        return null;
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
    public List<Staff> getAvailableDoctors(LocalDateTime dateTime) {
        try {
            return staffRepository.findAvailableDoctors(dateTime);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get available doctors: " + e.getMessage(), e);
        }
    }

    @Override
    public Staff getStaffById(Long id) {
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
    public Staff updateStaff(Long id, StaffRequest request) {
        try {
            Staff existingStaff = staffRepository.findStaffById(id)
                    .orElseThrow(() -> new NoSuchElementException("Staff  with id: " + id + " not found"));

            Staff updatedStaff = gson.fromJson(gson.toJson(request), Staff.class);
            updatedStaff.setStaffId(id);
            staffRepository.updateStaff(updatedStaff);

        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update staff: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void updateStaffStatus(Long staffId, String newStatus) {
        try {
            staffRepository.updateStaffStatus(staffId, newStatus);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update staff status: " + e.getMessage(), e);
        }
    }
}
