package com.tesa.hospitalerd.controller;

import com.tesa.hospitalerd.model.entity.Appointment;
import com.tesa.hospitalerd.model.request.AppointmentRequest;
import com.tesa.hospitalerd.model.request.AppointmentRescheduleRequest;
import com.tesa.hospitalerd.model.response.AppointmentResponse;
import com.tesa.hospitalerd.model.response.ApiResponse;
import com.tesa.hospitalerd.service.interfaces.AppointmentService;
import com.tesa.hospitalerd.util.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ModelMapper mapper;

    public AppointmentController(AppointmentService appointmentService, ModelMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentResponse>> bookAppointment(
             @RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentService.bookAppointment(request);
        AppointmentResponse resp = mapper.map(appointment, AppointmentResponse.class);
        return ResponseEntity.created(URI.create("/api/appointments/" + appointment.getAppointmentId()))
                .body(ResponseBuilder.success("Appointment booked successfully!", resp));
    }

    @PutMapping("/{id}/reschedule")
    public ResponseEntity<ApiResponse<AppointmentResponse>> rescheduleAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentRescheduleRequest request) {
        Appointment appointment = appointmentService.rescheduleAppointment(id, request);
        AppointmentResponse resp = mapper.map(appointment, AppointmentResponse.class);
        return ResponseEntity.ok(ResponseBuilder.success("Appointment rescheduled successfully!", resp));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(ResponseBuilder.success("Appointment cancelled successfully!", null));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<ApiResponse<java.util.List<AppointmentResponse>>> getUpcomingAppointments() {
        java.util.List<Appointment> appointments = appointmentService.getUpcomingAppointments();
        java.util.List<AppointmentResponse> resp = appointments.stream()
                .map(a -> mapper.map(a, AppointmentResponse.class))
                .toList();
        String msg = resp.isEmpty() ? "No upcoming appointments found" : "Upcoming appointments fetched successfully";
        return ResponseEntity.ok(ResponseBuilder.success(msg, resp));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ApiResponse<java.util.List<AppointmentResponse>>> getPatientAppointments(
            @PathVariable Long patientId) {
        java.util.List<Appointment> appointments = appointmentService.getPatientAppointments(patientId);
        java.util.List<AppointmentResponse> resp = appointments.stream()
                .map(a -> mapper.map(a, AppointmentResponse.class))
                .toList();
        String msg = resp.isEmpty() ? "No appointments found for patient" : "Patient appointments fetched successfully";
        return ResponseEntity.ok(ResponseBuilder.success(msg, resp));
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<ApiResponse<java.util.List<AppointmentResponse>>> getStaffAppointments(
            @PathVariable Long staffId) {
        java.util.List<Appointment> appointments = appointmentService.getStaffAppointments(staffId);
        java.util.List<AppointmentResponse> resp = appointments.stream()
                .map(a -> mapper.map(a, AppointmentResponse.class))
                .toList();
        String msg = resp.isEmpty() ? "No appointments found for staff" : "Staff appointments fetched successfully";
        return ResponseEntity.ok(ResponseBuilder.success(msg, resp));
    }
}
