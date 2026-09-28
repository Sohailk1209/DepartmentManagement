package com.sohail.DepartmentManagement.repository;

import com.sohail.DepartmentManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department , Long> {
    boolean existsByTitle(String title);
}
