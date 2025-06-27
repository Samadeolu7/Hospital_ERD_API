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
public class Staff {

    private int staffId;
    private String staffFirstName;
    private String staffLastName;
    private StaffRole staffRole;
    private String staffSpecialization;
    private String staffDepartment;
    private String staffPhoneNumber;
    private String staffEmail;
    private StaffStatus staffStatus;
    private LocalDateTime staffCreatedAt;
    private LocalDateTime staffUpdated;
}
