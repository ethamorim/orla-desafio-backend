package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.controller.resource.EmployeeResource;
import com.ethamorim.orlachallengebackend.exception.NoRecordWithGivenId;
import com.ethamorim.orlachallengebackend.model.Department;
import com.ethamorim.orlachallengebackend.model.Employee;
import com.ethamorim.orlachallengebackend.repository.DepartmentRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;
    private final DepartmentRepository departmentRepository;
    public EmployeeController(EmployeeRepository repository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Employee> getEmployee() {
        return repository.findAll();
    }

    @PostMapping
    public void postEmployee(@RequestBody EmployeeResource newEmployee) {
        var department = departmentRepository.findById(newEmployee.departmentId());
        if (department.isEmpty()) {
            throw new NoRecordWithGivenId("There is no department with the given id");
        }
    }

}
