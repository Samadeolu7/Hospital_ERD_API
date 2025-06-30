package com.tesa.hospitalerd.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    private Long appointmentId;
    private Long patientId;
    private Long staffId;
    private LocalDateTime appointmentDateTime;
    private Integer appointmentDurationMinutes;
    private String appointmentReason;
    private String appointmentStatus;
    private LocalDateTime appointmentCreatedAt;
    private LocalDateTime appointmentUpdatedAt;
}
