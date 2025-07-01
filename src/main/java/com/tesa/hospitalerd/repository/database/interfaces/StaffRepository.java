package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.Staff;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StaffRepository {

    void createStaff(Staff staff);
    List<Staff> findAllStaffs();
    List<Staff> findAllDoctors();
    List<Staff> findAvailableDoctors(LocalDateTime dateTime);
    Optional<Staff> findStaffById(Long id);
    List<Staff> staffSearch(String query);
    void updateStaff(Staff staff);
    void updateStaffStatus(Long staffId,String newStatus);
}