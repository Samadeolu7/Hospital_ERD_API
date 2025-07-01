package com.tesa.hospitalerd.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffResponse {
    private Long staffId;
    private String staffFirstName;
    private String staffLastName;
    private String staffRole;
    private String staffSpecialization;
    private String staffDepartment;
    private String staffPhoneNumber;
    private String staffEmail;
    private String staffStatus;
    private LocalDateTime staffCreatedAt;
    private LocalDateTime staffUpdatedAt;
}
