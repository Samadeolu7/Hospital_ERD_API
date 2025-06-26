package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Prescription entity access
 */
@Repository
public interface PrescriptionRepository {
    int createPrescription(Prescription prescription);

    Prescription findPrescriptionByPrescriptionID(Long prescriptionID);

    int updatePrescription(Prescription prescription);

    int deletePrescriptionByPrescriptionID(Long prescriptionID);

    List<Prescription> findByPatientId(Long patientId);

    List<Prescription> findByDoctorId(Long doctorId);

    List<Prescription> findExpiredUnfulfilled();
}
