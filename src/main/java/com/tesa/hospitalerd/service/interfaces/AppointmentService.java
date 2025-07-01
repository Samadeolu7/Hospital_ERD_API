package com.tesa.hospitalerd.service.interfaces;

import com.tesa.hospitalerd.model.entity.Appointment;
import com.tesa.hospitalerd.model.request.AppointmentRequest;
import com.tesa.hospitalerd.model.request.AppointmentRescheduleRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    Appointment bookAppointment(AppointmentRequest request);
    Appointment rescheduleAppointment(Long id, AppointmentRescheduleRequest request);
    void cancelAppointment(Long id);
    List<Appointment> getUpcomingAppointments();
    List<Appointment> getPatientAppointments(Long patientId);
    List<Appointment> getStaffAppointments(Long staffId);
    boolean  hasSchedulingConflict(Long staffId, LocalDateTime appointmentDateTime, int appointmentDurationMinutes);
}
