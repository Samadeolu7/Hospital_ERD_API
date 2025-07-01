package com.tesa.hospitalerd.repository.database.query;

public class AppointmentQuery {

    public static final String BOOK_APPOINTMENT = """
            INSERT INTO TIS_APPOINTMENT
            (patientId,
            staffId,
            appointmentDateTime,
            appointmentDurationMinutes,
            appointmentReason,
            appointmentStatus
            appointmentCreatedAt
            appointmentUpdatedAt)
            VALUES
            (:patientId,
            :staffId,
            :appointmentDateTime,
            :appointmentDurationMinutes,
            :appointmentReason,
            'SCHEDULED'
            CURRENT_TIMESTAMP,
            CURRENT_TIMESTAMP)
            RETURNING appointmentId
            """;

    public static final String RESCHEDULE_APPOINTMENT = """
            UPDATE TIS_APPOINTMENT
            SET appointmentDateTime = :appointmentNewDateTime,
            appointmentDurationMinutes = :appointmentDurationMinutes
            WHERE appointmentId = :appointmentId
            """;

    public static final String CANCEL_APPOINTMENT = """
            UPDATE FROM TIS_APPOINTMENT
            SET status = 'CANCELLED'
            WHERE appointmentId = :appointmentId
            """;

    public static final String FIND_UPCOMING_APPOINTMENTS = """
            SELECT * FROM TIS_APPOINTMENT
            WHERE appointmentDateTime > NOW()
            AND appointmentStatus = 'SCHEDULED'
            ORDER BY appointmentDateTime
            """;

    public static final String FIND_APPOINTMENTS_BY_PATIENT_ID = """
            SELECT * FROM TIS_APPOINTMENT
            WHERE patient_id = :patientId
            ORDER BY appointmentDateTime DESC
            """;

    public static final String FIND_APPOINTMENTS_BY_STAFF_ID = """
            SELECT * FROM TIS_APPOINTMENT
            WHERE staffId = :staffId
            ORDER BY appointmentDateTime DESC
            """;

    public static final String FIND_APPOINTMENT_BY_ID = """
            SELECT * FROM TIS_APPOINTMENT
            WHERE appointmentId = :appointmentId
            """;

    public static final String HAS_SCHEDULING_CONFLICT = """
            SELECT EXISTS (
            SELECT 1 FROM TIS_APPOINTMENT
            WHERE staffId = :staffId
            AND staffStatus = 'SCHEDULED'
            AND (
            -- New appointment starts during existing appointment
            (:appointmentDateTime >= appointmentDateTime
            AND :appointmentDateTime <
            appointmentDateTime + make_interval(mins => appointmentDurationMinutes))
            OR
            -- New appointment ends during existing appointment
            (:appointmentDateTime + make_interval(mins => :appointmentDurationMinutes) > appointmentDateTime
            AND :appointmentDateTime + make_interval(mins => :appointmentDurationMinutes) <=
            appointmentDateTime + make_interval(mins => appointmentDurationMinutes))
            OR
            -- New appointment completely overlaps existing appointment
            (:appointmentDateTime <= appointmentDateTime
            AND :appointmentDateTime + make_interval(mins => :appointmentDurationMinutes) >=
            appointmentDateTime + make_interval(mins => appointmentDurationMinutes))
            )
            )
            """;

    public static final String HAS_SCHEDULING_ID_CONFLICT = """
            SELECT EXISTS (
            SELECT 1 FROM TIS_APPOINTMENT
            WHERE staffId = :staffId
            AND id != :excludeAppointmentId
            AND staffStatus = 'SCHEDULED'
            AND (
            -- New appointment starts during existing appointment
            (:appointmentDateTime >= appointmentDateTime
            AND :appointmentDateTime <
            appointmentDateTime + make_interval(mins => appointmentDurationMinutes))
            OR
            -- New appointment ends during existing appointment
            (:appointmentDateTime + make_interval(mins => :appointmentDurationMinutes) > appointmentDateTime
            AND :appointmentDateTime + make_interval(mins => :appointmentDurationMinutes) <=
            appointmentDateTime + make_interval(mins => appointmentDurationMinutes))
            OR
            -- New appointment completely overlaps existing appointment
            (:appointmentDateTime <= appointmentDateTime
            AND :appointmentDateTime + make_interval(mins => :appointmentDurationMinutes) >=
            appointmentDateTime + make_interval(mins => appointmentDurationMinutes))
            )
            )
            """;


}
