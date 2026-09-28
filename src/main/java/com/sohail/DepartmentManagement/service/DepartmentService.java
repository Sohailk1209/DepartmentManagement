package com.sohail.DepartmentManagement.service;

import com.sohail.DepartmentManagement.dto.DepartmentDTO;
import com.sohail.DepartmentManagement.entity.Department;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Department createDepartment(DepartmentDTO departmentDTO);

    List<Department> getAllDepartment();

    Department getDepartmentById(Long id);

    Boolean deleteDepartmentWithId(Long id);

    Department updateDepartmentWithId(Long id , DepartmentDTO update);

    Department partialUpdateDepartmentWithId(Long id, Map<String, Object> updates) throws IllegalAccessException;
}
