package com.tesa.hospitalerd.service;

import com.tesa.hospitalerd.model.dto.StaffRequest;
import com.tesa.hospitalerd.model.entity.Staff;

import java.util.List;

public interface StaffService {

    void createStaff(StaffRequest request);
    List<Staff> getAllStaffs();
    List<Staff> getAllDoctors();
    List<Staff> getAvailableDoctors(String date, String time);
    Staff getStaffById(int id);
    List<Staff> searchStaff(String query);
    void updateStaff(int id, StaffRequest request);

}
