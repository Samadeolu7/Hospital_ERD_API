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
    Equipment getEquipmentById(Long id);
    List<Equipment> searchEquipment(String query);
    List<Equipment> getEquipmentByStatus(String status);
    void markEquipmentForMaintenance(Long id);
    void markEquipmentAsAvailable(Long id);
    void decommissionEquipment(Long id);
    void updateEquipment(Long id, EquipmentRequest request);

//    interface PatientService {
//
//        void createPatient(PatientRequest request);
//        List<Patient> getAllPatients();
//        Patient getPatientById(int id);
//        List<Patient> searchPatient(String query);
//        void updatePatient(int id, PatientRequest request);
//    }
}
