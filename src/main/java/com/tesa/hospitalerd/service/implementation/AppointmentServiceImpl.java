package com.tesa.hospitalerd.service.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tesa.hospitalerd.model.entity.Appointment;
import com.tesa.hospitalerd.model.request.AppointmentRequest;
import com.tesa.hospitalerd.model.request.AppointmentRescheduleRequest;
import com.tesa.hospitalerd.repository.database.interfaces.AppointmentRepository;
import com.tesa.hospitalerd.repository.database.interfaces.PatientRepository;
import com.tesa.hospitalerd.repository.database.interfaces.StaffRepository;
import com.tesa.hospitalerd.service.interfaces.AppointmentService;
import com.tesa.hospitalerd.util.LocalDateTimeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository, StaffRepository staffRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public Appointment bookAppointment(AppointmentRequest request) {
        try {
            // Validate patient exists
            patientRepository.findPatientById(request.getPatientId())
                    .orElseThrow(() -> new NoSuchElementException(
                            "Patient not found with id: " + request.getPatientId()));

            // Validate staff exists and is active
            staffRepository.findStaffById(request.getStaffId())
                    .filter(staff -> "ACTIVE".equals(staff.getStaffStatus()))
                    .orElseThrow(() -> new NoSuchElementException(
                            "Staff not available or inactive"));

            // Check for scheduling conflicts
            if (appointmentRepository.hasSchedulingConflict(
                    request.getStaffId(),
                    request.getAppointmentDateTime(),
                    request.getAppointmentDurationMinutes())) {
                throw new InvalidDnDOperationException("Scheduling conflict detected");
            }

            Appointment appointment = new Appointment();
            appointment.setPatientId(request.getPatientId());
            appointment.setStaffId(request.getStaffId());
            appointment.setAppointmentDateTime(request.getAppointmentDateTime());
            appointment.setAppointmentDurationMinutes(request.getAppointmentDurationMinutes());
            appointment.setAppointmentReason(request.getAppointmentReason());

            Long appointmentId = appointmentRepository.bookAppointment(appointment);
            appointment.setAppointmentId(appointmentId);

            return appointment;


        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to book appointment: " + e.getMessage(), e);
        }
    }

    @Override
    public Appointment rescheduleAppointment(Long id, AppointmentRescheduleRequest request) {
        try {
            // Validate appointment exists
            appointmentRepository.findAppointmentById(id)
                    .orElseThrow(() -> new NoSuchElementException(
                            "Appointment not found with id: " + id));

            // Check for new scheduling conflicts
            if (appointmentRepository.hasSchedulingIdConflict(
                    request.getStaffId(),
                    request.getAppointmentNewDateTime(),
                    request.getAppointmentDurationMinutes(),
                    id)) {
                throw new InvalidDnDOperationException("Scheduling conflict detected");
            }

            appointmentRepository.rescheduleAppointment(
                    id,
                    request.getAppointmentNewDateTime(),
                    request.getAppointmentDurationMinutes());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get equipments: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void cancelAppointment(Long id) {
        try {
            appointmentRepository.cancelAppointment(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to cancel appointment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> getUpcomingAppointments() {
        try {
            return appointmentRepository.findUpcomingAppointments();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to cancel appointment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> getPatientAppointments(Long patientId) {
        try {
            return appointmentRepository.findAppointmentsByPatientId(patientId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to find appointments: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> getStaffAppointments(Long staffId) {
        try {
            return appointmentRepository.findAppointmentsByStaffId(staffId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to find appointments: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean hasSchedulingConflict(Long staffId, LocalDateTime appointmentDateTime, int appointmentDurationMinutes) {
        return false;
    }
}
