package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.Prescription;
import com.tesa.hospitalerd.model.request.PrescriptionCreateRequest;
import com.tesa.hospitalerd.model.request.PrescriptionUpdateRequest;

import java.util.List;

public interface PrescriptionService {
    Prescription createPrescription(PrescriptionCreateRequest req);

    Prescription updatePrescription(PrescriptionUpdateRequest req);

    void delete(Long id);
    Prescription findById(Long id);
    List<Prescription> findByPatientId(Long patientId);
    List<Prescription> findByDoctorId(Long doctorId);
    List<Prescription> findExpiredUnfulfilled();
}
