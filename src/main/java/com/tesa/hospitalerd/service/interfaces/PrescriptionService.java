package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.Prescription;

import java.util.List;

public interface PrescriptionService {
    Prescription create(Prescription prescription);
    Prescription update(Prescription prescription);
    void delete(Long id);
    Prescription findById(Long id);
    List<Prescription> findByPatientId(Long patientId);
    List<Prescription> findByDoctorId(Long doctorId);
    List<Prescription> findExpiredUnfulfilled();
}
