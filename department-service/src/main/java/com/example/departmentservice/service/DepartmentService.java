package com.example.departmentservice.service;

import com.example.departmentservice.entity.Department;
import org.springframework.stereotype.Service;

public interface DepartmentService {
    Department saveDepartment(Department department);

    Department findDepartmentById(Long departmentId);
}
