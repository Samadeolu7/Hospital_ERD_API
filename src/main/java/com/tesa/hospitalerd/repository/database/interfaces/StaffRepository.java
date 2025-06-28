package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository {

    void createStaff(Staff staff);
    List<Staff> findAllStaffs();
    List<Staff> findAllDoctors();
    List<Staff> findAvailableDoctors(String date, String time);
    Optional<Staff> findStaffById(int id);
    List<Staff> staffSearch(String query);
    void updateStaff(Staff staff);
}