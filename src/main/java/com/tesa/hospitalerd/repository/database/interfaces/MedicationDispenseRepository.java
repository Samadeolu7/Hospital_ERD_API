package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.MedicationDispense;
import com.tesa.hospitalerd.model.entity.Staff;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * MedicationDispense access
 */
@Repository
public interface MedicationDispenseRepository{
    int createMedicationDispense(MedicationDispense medicationDispense);

    int updateMedicationDispense(MedicationDispense medicationDispense);

    int  deleteMedicationDispenseById(Long id);

    MedicationDispense findMedicationDispenseById(Long id);

    List<MedicationDispense> findMedicationDispenseByMedicationID(Long medicationId);

    List<MedicationDispense> findByDispenserId(Long staffId);

    List<MedicationDispense> findByDispensedAtBetween(LocalDate start, LocalDate end);

    interface StaffRepository {

        void createStaff(Staff staff);
        List<Staff> findAllStaffs();
        List<Staff> findAllDoctors();
        List<Staff> findAvailableDoctors(String date, String time);
        Optional<Staff> findStaffById(int id);
        List<Staff> staffSearch(String query);
        void updateStaff(Staff staff);
    }
}

