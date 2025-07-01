package com.tesa.hospitalerd.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRescheduleRequest {

    private Long staffId;
    private LocalDateTime appointmentNewDateTime;
    private Integer appointmentDurationMinutes;
    private String appointmentReason;
}
