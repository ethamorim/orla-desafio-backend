package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.controller.resource.Department;
import com.ethamorim.orlachallengebackend.model.DepartmentModel;
import com.ethamorim.orlachallengebackend.repository.DepartmentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por gerenciar as requisições relacionadas a departamentos.
 * Disponibiliza as APIs abaixo:
 * - GET /departments
 * - POST /departments
 *
 * @author ethamorim
 */
@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    private final DepartmentsRepository repository;
    public DepartmentsController(DepartmentsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<DepartmentModel> getDepartments() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<DepartmentModel> postDepartment(@RequestBody Department department) {
        var newDepartment = new DepartmentModel(department.name(), department.email());
        return new ResponseEntity<>(repository.save(newDepartment), HttpStatus.CREATED);
    }
}
