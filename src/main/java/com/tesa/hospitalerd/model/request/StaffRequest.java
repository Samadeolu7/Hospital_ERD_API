package com.tesa.hospitalerd.model.request;

import com.tesa.hospitalerd.model.entity.StaffRole;
import com.tesa.hospitalerd.model.entity.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffRequest {
    private String staffFirstName;
    private String staffLastName;
    private StaffRole staffRole;
    private String staffSpecialization;
    private String staffDepartment;
    private String staffPhoneNumber;
    private String staffEmail;
    private StaffStatus staffStatus;
}
