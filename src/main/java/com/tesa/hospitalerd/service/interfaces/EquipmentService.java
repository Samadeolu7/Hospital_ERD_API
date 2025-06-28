package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.request.EquipmentRequest;
import com.tesa.hospitalerd.model.request.PatientRequest;
import com.tesa.hospitalerd.model.entity.Equipment;
import com.tesa.hospitalerd.model.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {

    void createEquipment(EquipmentRequest request);
    List<Equipment> getAllEquipments();
    Equipment getEquipmentById(int id);
    List<Equipment> searchEquipment(String query);
    List<Equipment> getEquipmentByStatus(String status);
    void markEquipmentForMaintenance(int id);
    void markEquipmentAsAvailable(int id);
    void decommissionEquipment(int id);
    void updateEquipment(int id, EquipmentRequest request);

    interface PatientService {

        void createPatient(PatientRequest request);
        List<Patient> getAllPatients();
        Patient getPatientById(int id);
        List<Patient> searchPatient(String query);
        void updatePatient(int id, PatientRequest request);
    }
}
