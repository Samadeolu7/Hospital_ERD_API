package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.Appointment;
import com.tesa.hospitalerd.repository.database.interfaces.AppointmentRepository;
import com.tesa.hospitalerd.repository.database.query.AppointmentQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AppointmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long bookAppointment(Appointment appointment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("patientId", appointment.getPatientId())
                .addValue("staffId", appointment.getStaffId())
                .addValue("appointmentDateTime", appointment.getAppointmentDateTime())
                .addValue("durationMinutes", appointment.getAppointmentReason());
       return jdbcTemplate.queryForObject(AppointmentQuery.BOOK_APPOINTMENT, params, Long.class);

    }

    @Override
    public void rescheduleAppointment(Long id, LocalDateTime newDateTime, int appointmentDuration) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", id)
                .addValue("newDateTime", newDateTime)
                .addValue("duration", appointmentDuration);
        jdbcTemplate.update(AppointmentQuery.RESCHEDULE_APPOINTMENT, params);
    }

    @Override
    public void cancelAppointment(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("appointmentId", id);
        jdbcTemplate.update(AppointmentQuery.CANCEL_APPOINTMENT, params);
    }

    @Override
    public Optional<Appointment> findAppointmentById(Long id) {
        try {
            Appointment appointment = jdbcTemplate.queryForObject(
                    AppointmentQuery.FIND_APPOINTMENT_BY_ID,
                    new MapSqlParameterSource("studentId", id),
                    new BeanPropertyRowMapper<>(Appointment.class));
            return Optional.ofNullable(appointment);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Appointment> findUpcomingAppointments() {
        return jdbcTemplate.query(AppointmentQuery.FIND_UPCOMING_APPOINTMENTS, new BeanPropertyRowMapper<>(Appointment.class));
    }

    @Override
    public List<Appointment> findAppointmentsByPatientId(Long patientId) {
        return jdbcTemplate.query(AppointmentQuery.FIND_APPOINTMENTS_BY_PATIENT_ID, new BeanPropertyRowMapper<>(Appointment.class));
    }

    @Override
    public List<Appointment> findAppointmentsByStaffId(Long staffId) {
        return jdbcTemplate.query(AppointmentQuery.FIND_APPOINTMENTS_BY_STAFF_ID, new BeanPropertyRowMapper<>(Appointment.class));
    }

    @Override
    public boolean hasSchedulingConflict(Long staffId, LocalDateTime appointmentDateTime, int appointmentDurationMinutes) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffId", staffId)
                .addValue("appointmentDateTime", appointmentDateTime)
                .addValue("durationMinutes", appointmentDurationMinutes);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(AppointmentQuery.HAS_SCHEDULING_CONFLICT, params, Boolean.class));
    }

    @Override
    public boolean hasSchedulingIdConflict(Long staffId, LocalDateTime appointmentNewDateTime, int appointmentDurationMinutes, Long excludeAppointmentId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffId", staffId)
                .addValue("appointmentNewDateTime", appointmentNewDateTime)
                .addValue("appointmentDurationMinutes", appointmentDurationMinutes)
                .addValue("excludeAppointmentId", excludeAppointmentId);

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(AppointmentQuery.HAS_SCHEDULING_ID_CONFLICT, params, Boolean.class));
    }
}
