package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.request.EquipmentRequest;
import com.tesa.hospitalerd.model.entity.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {

    Equipment createEquipment(EquipmentRequest request);
    List<Equipment> getAllEquipments();
    Equipment getEquipmentById(Long id);
    List<Equipment> searchEquipment(String query);
    List<Equipment> getEquipmentByStatus(String status);
    Equipment markEquipmentForMaintenance(Long id);
    Equipment markEquipmentAsAvailable(Long id);
    Equipment decommissionEquipment(Long id);
    Equipment updateEquipment(Long id, EquipmentRequest request);

//    interface PatientService {
//
//        void createPatient(PatientRequest request);
//        List<Patient> getAllPatients();
//        Patient getPatientById(int id);
//        List<Patient> searchPatient(String query);
//        void updatePatient(int id, PatientRequest request);
//    }
}
