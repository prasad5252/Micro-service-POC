package com.example.departmentservice.controller;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department/v1")
@Slf4j
public class DepartmentResource {

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        log.info("inside saveDepartment");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("inside findDepartmentById");
        return departmentService.findDepartmentById(departmentId);
    }
}
