package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.Staff;
import com.tesa.hospitalerd.model.request.StaffRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface StaffService {

    void createStaff(StaffRequest request);
    List<Staff> getAllStaffs();
    List<Staff> getAllDoctors();
    List<Staff> getAvailableDoctors(LocalDateTime dateTime);
    Staff getStaffById(Long id);
    List<Staff> searchStaff(String query);
    void updateStaff(Long id, StaffRequest request);
    void updateStaffStatus(Long staffId,String newStatus);

}
