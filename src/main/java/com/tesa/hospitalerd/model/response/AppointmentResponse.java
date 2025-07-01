package com.tesa.hospitalerd.model.response;

import com.tesa.hospitalerd.model.entity.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponse {
    private Long appointmentId;
    private Long patientId;
    private Long staffId;
    private LocalDateTime appointmentDateTime;
    private Integer appointmentDurationMinutes;
    private String appointmentReason;
    private AppointmentStatus appointmentStatus;
    private LocalDateTime appointmentCreatedAt;
    private LocalDateTime appointmentUpdatedAt;
}
