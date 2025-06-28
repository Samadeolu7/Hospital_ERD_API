package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.MedicationInventory;
import com.tesa.hospitalerd.model.entity.Staff;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationDispenseRepository;
import com.tesa.hospitalerd.repository.database.interfaces.MedicationInventoryRepository;
import com.tesa.hospitalerd.repository.database.query.MedicationInventoryQuery;
import com.tesa.hospitalerd.repository.database.query.MedicationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

/**
 * JDBC implementation for MedicationInventoryRepository
 */
@Repository
public class MedicationInventoryRepositoryImpl implements MedicationInventoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public MedicationInventoryRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createMedicationInventory(MedicationInventory medicationInventory) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("medicationID", medicationInventory.getMedicationID())
                .addValue("location", medicationInventory.getMedicationInventoryLocation())
                .addValue("totalQuantity", medicationInventory.getMedicationInventoryTotalQuantity())
                .addValue("availableQuantity", medicationInventory.getMedicationInventoryAvailableQuantity())
                .addValue("batchNumber", medicationInventory.getMedicationInventoryBatchNo())
                .addValue("expiryDate", medicationInventory.getMedicationInventoryExpiryDate());
        return jdbcTemplate.update(MedicationInventoryQuery.CREATE_INVENTORY, params);
    }

    @Override
    public int updateMedicationInventory(MedicationInventory medicationInventory) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", medicationInventory.getMedicationID())
                .addValue("medicationID", medicationInventory.getMedicationID())
                .addValue("location", medicationInventory.getMedicationInventoryLocation())
                .addValue("totalQuantity", medicationInventory.getMedicationInventoryTotalQuantity())
                .addValue("availableQuantity", medicationInventory.getMedicationInventoryAvailableQuantity())
                .addValue("batchNumber", medicationInventory.getMedicationInventoryBatchNo())
                .addValue("expiryDate", medicationInventory.getMedicationInventoryID())
                .addValue("status", medicationInventory.getMedicationInventoryStatus());
        return jdbcTemplate.update(MedicationInventoryQuery.UPDATE_INVENTORY, params);
    }

    @Override
    public int deleteMedicationInventoryById(Long id) {
        return jdbcTemplate.update(
                MedicationInventoryQuery.DELETE_INVENTORY,
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public MedicationInventory findMedicationInventoryById(Long id) {
        return jdbcTemplate.queryForObject(
                MedicationInventoryQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }

    @Override
    public List<MedicationInventory> findMedicationInventoryByMedicationID(Long medicationId) {
        return jdbcTemplate.query(
                MedicationInventoryQuery.FIND_BY_MEDICATION,
                new MapSqlParameterSource("medId", medicationId),
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }

    @Override
    public List<MedicationInventory> findByExpiryDateBefore(LocalDate date) {
        return jdbcTemplate.query(
                MedicationInventoryQuery.FIND_EXPIRED,
                new MapSqlParameterSource("date", date),
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }

    @Override
    public List<MedicationInventory> findLowStock() {
        return jdbcTemplate.query(
                MedicationInventoryQuery.FIND_LOW_STOCK,
                new BeanPropertyRowMapper<>(MedicationInventory.class)
        );
    }

    @Repository
    public static class StaffRepositoryImpl implements MedicationDispenseRepository.StaffRepository {

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
            jdbcTemplate.update(MedicationQuery.StaffQuery.INSERT_STAFF, params);
        }

        @Override
        public List<Staff> findAllStaffs() {
            return jdbcTemplate.query(MedicationQuery.StaffQuery.FIND_ALL_STAFF, new BeanPropertyRowMapper<>(Staff.class));
        }

        @Override
        public List<Staff> findAllDoctors() {
            return jdbcTemplate.query(MedicationQuery.StaffQuery.FIND_ALL_DOCTORS, new BeanPropertyRowMapper<>(Staff.class));
        }

        @Override
        public List<Staff> findAvailableDoctors(String date, String time) {
            return jdbcTemplate.query(MedicationQuery.StaffQuery.FIND_AVAILABLE_DOCTORS, new BeanPropertyRowMapper<>(Staff.class));
        }

        @Override
        public Optional<Staff> findStaffById(int id) {
            try {
                Staff staff = jdbcTemplate.queryForObject(
                        MedicationQuery.StaffQuery.FIND_STAFF_BY_ID,
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
                    MedicationQuery.StaffQuery.SEARCH_STAFF,
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
            jdbcTemplate.update(MedicationQuery.StaffQuery.UPDATE_STAFF, params);
        }
    }
}
