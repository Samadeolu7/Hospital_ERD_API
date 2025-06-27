package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.Staff;
import com.tesa.hospitalerd.repository.database.interfaces.StaffRepository;
import com.tesa.hospitalerd.repository.database.query.StaffQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StaffRepositoryImpl implements StaffRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StaffRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createStaff(Staff staff) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffFirstName", staff.getStaffFirstName())
                .addValue("staffLastName", staff.getStaffLastName())
                .addValue("staffRole", staff.getStaffRole())
                .addValue("staffSpecialization", staff.getStaffSpecialization())
                .addValue("staffPhoneNumber", staff.getStaffPhoneNumber())
                .addValue("staffDepartment", staff.getStaffDepartment())
                .addValue("staffPhoneNumber", staff.getStaffEmail())
                .addValue("staffStatus", staff.getStaffStatus());
        jdbcTemplate.update(StaffQuery.INSERT_STAFF, params);
    }

    @Override
    public List<Staff> findAllStaffs() {
        return jdbcTemplate.query(StaffQuery.FIND_ALL_STAFF, new BeanPropertyRowMapper<>(Staff.class));
    }

    @Override
    public List<Staff> findAllDoctors() {
        return jdbcTemplate.query(StaffQuery.FIND_ALL_DOCTORS, new BeanPropertyRowMapper<>(Staff.class));
    }

    @Override
    public List<Staff> findAvailableDoctors(String date, String time) {
        return jdbcTemplate.query(StaffQuery.FIND_AVAILABLE_DOCTORS, new BeanPropertyRowMapper<>(Staff.class));
    }

    @Override
    public Optional<Staff> findStaffById(int id) {
        try {
            Staff staff = jdbcTemplate.queryForObject(
                    StaffQuery.FIND_STAFF_BY_ID,
                    new MapSqlParameterSource("staffId", id),
                    new BeanPropertyRowMapper<>(Staff.class));
            return Optional.ofNullable(staff);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Staff> staffSearch(String query) {
        return jdbcTemplate.query(
                StaffQuery.SEARCH_STAFF,
                new MapSqlParameterSource("query", "%" + query + "%"),
                new BeanPropertyRowMapper<>(Staff.class));
    }

    @Override
    public void updateStaff(Staff staff) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("staffId", staff.getStaffId())
                .addValue("staffFirstName", staff.getStaffFirstName())
                .addValue("staffLastName", staff.getStaffLastName())
                .addValue("staffRole", staff.getStaffRole())
                .addValue("staffSpecialization", staff.getStaffSpecialization())
                .addValue("staffPhoneNumber", staff.getStaffPhoneNumber())
                .addValue("staffDepartment", staff.getStaffDepartment())
                .addValue("staffPhoneNumber", staff.getStaffEmail())
                .addValue("staffStatus", staff.getStaffStatus());
        jdbcTemplate.update(StaffQuery.UPDATE_STAFF, params);
    }
}
