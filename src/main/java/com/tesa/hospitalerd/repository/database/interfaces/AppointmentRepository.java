package com.tesa.hospitalerd.repository.database.interfaces;

import com.tesa.hospitalerd.model.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    Long bookAppointment(Appointment appointment);
    void rescheduleAppointment(Long id, LocalDateTime newDateTime, int appointmentDuration);
    void cancelAppointment(Long id);
    Optional<Appointment> findAppointmentById(Long id);
    List<Appointment> findUpcomingAppointments();
    List<Appointment> findAppointmentsByPatientId(Long patientId);
    List<Appointment> findAppointmentsByStaffId(Long staffId);
    boolean  hasSchedulingConflict(Long staffId, LocalDateTime appointmentDateTime, int appointmentDurationMinutes);
    boolean  hasSchedulingIdConflict(Long staffId, LocalDateTime appointmentDateTime, int appointmentDurationMinutes, Long excludeAppointmentId);

}
