package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.Appointment;
import com.tesa.hospitalerd.model.request.AppointmentRequest;
import com.tesa.hospitalerd.model.request.AppointmentRescheduleRequest;
import com.tesa.hospitalerd.service.interfaces.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public ResponseEntity<Appointment> bookAppointment(
             @RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(request);
        return ResponseEntity
                .created(URI.create("/api/appointments/" + appointment.getAppointmentId()))
                .body(appointment);
    }

    @PutMapping("/{id}/reschedule")
    public ResponseEntity<?> rescheduleAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentRescheduleRequest request) {
        appointmentService.rescheduleAppointment(id, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingAppointments() {
        appointmentService.getUpcomingAppointments();
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getPatientAppointments(
            @PathVariable Long patientId) {
        appointmentService.getPatientAppointments(patientId);
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<?> getStaffAppointments(
            @PathVariable Long staffId) {
        appointmentService.getStaffAppointments(staffId);
        return ResponseEntity.ok().body("");
    }
}
