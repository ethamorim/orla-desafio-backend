package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.controller.resource.EmployeeResource;
import com.ethamorim.orlachallengebackend.exception.NoRecordFoundException;
import com.ethamorim.orlachallengebackend.model.Department;
import com.ethamorim.orlachallengebackend.model.Employee;
import com.ethamorim.orlachallengebackend.repository.DepartmentRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void postEmployee(@RequestBody EmployeeResource employee) {
        Optional<Department> optionalDepartment;
        if (employee.departmentEmail() != null) {
            optionalDepartment = departmentRepository.findByEmail(employee.departmentEmail());
        } else {
            optionalDepartment = departmentRepository.findById(employee.departmentId());
        }
        var department = optionalDepartment.orElseThrow(() -> new NoRecordFoundException("There is no department with the given id"));
        var newEmployee = new Employee(
                employee.name(),
                employee.email(),
                employee.cpf(),
                employee.startDate(),
                employee.income(),
                employee.isManager(),
                department
        );
        this.repository.save(newEmployee);
    }

}
