// Package: com.tesa.hospitalerd.repository.database.impl

package com.tesa.hospitalerd.repository.database.implementation;

import com.tesa.hospitalerd.model.entity.*;
import com.tesa.hospitalerd.repository.database.interfaces.*;
import com.tesa.hospitalerd.repository.database.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * /**
 * JDBC implementation for EquipmentAssignmentRepository
 */
@Repository
@Primary
public class EquipmentAssignmentRepositoryImpl implements EquipmentAssignmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EquipmentAssignmentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createEquipmentAssignment(EquipmentAssignment equipmentAssignment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("equipmentInventoryID", equipmentAssignment.getEquipmentInventoryID())
                .addValue("equipmentAssignedTo", equipmentAssignment.getEquipmentAssignedTo())
                .addValue("equipmentAssignedAt", equipmentAssignment.getEquipmentAssignedAt())
                .addValue("equipmentGivenBy", equipmentAssignment.getEquipmentGivenBy());
        return jdbcTemplate.update(EquipmentAssignmentQuery.CREATE_ASSIGNMENT, params);
    }

    @Override
    public int updateEquipmentAssignment(EquipmentAssignment equipmentAssignment) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", equipmentAssignment.getEquipmentAssignmentID())
                .addValue("equipmentInventoryID", equipmentAssignment.getEquipmentInventoryID())
                .addValue("equipmentAssignedTo", equipmentAssignment.getEquipmentAssignedTo())
                .addValue("equipmentAssignedAt", equipmentAssignment.getEquipmentAssignedAt())
                .addValue("equipmentReturnedAT", equipmentAssignment.getEquipmentReturnedAt())
                .addValue("equipmentReceivedBy", equipmentAssignment.getEquipmentReceivedBy())
                .addValue("equipmentGivenBy", equipmentAssignment.getEquipmentGivenBy());
        return jdbcTemplate.update(EquipmentAssignmentQuery.UPDATE_ASSIGNMENT, params);
    }

    @Override
    public int deleteEquipmentAssignment(Integer id) {
        return jdbcTemplate.update(EquipmentAssignmentQuery.DELETE_ASSIGNMENT,
                new MapSqlParameterSource("id", id));
    }

    @Override
    public EquipmentAssignment findEquipmentAssignmentById(Integer id) {
        return jdbcTemplate.queryForObject(
                EquipmentAssignmentQuery.FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                new BeanPropertyRowMapper<>(EquipmentAssignment.class)
        );
    }

    @Override
    public List<EquipmentAssignment> findAllEquipmentAssignment() {
        return jdbcTemplate.query(
                EquipmentAssignmentQuery.FIND_ALL,
                new BeanPropertyRowMapper<>(EquipmentAssignment.class)
        );
    }

    @Override
    public List<EquipmentAssignment> findByStaffId(Long staffId) {
        return jdbcTemplate.query(
                EquipmentAssignmentQuery.FIND_BY_STAFF_ID,
                new MapSqlParameterSource("staffId", staffId),
                new BeanPropertyRowMapper<>(EquipmentAssignment.class)
        );
    }

    @Override
    public List<EquipmentAssignment> findByReturnedAtIsNull() {
        return jdbcTemplate.query(
                EquipmentAssignmentQuery.FIND_UNRETURNED,
                new BeanPropertyRowMapper<>(EquipmentAssignment.class)
        );
    }
}
