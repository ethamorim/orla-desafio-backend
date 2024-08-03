package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.controller.resource.Employee;
import com.ethamorim.orlachallengebackend.controller.resource.Project;
import com.ethamorim.orlachallengebackend.exception.NoRecordFoundException;
import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import com.ethamorim.orlachallengebackend.model.EmployeesProjectsModel;
import com.ethamorim.orlachallengebackend.model.ProjectModel;
import com.ethamorim.orlachallengebackend.repository.EmployeesProjectsRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeesRepository;
import com.ethamorim.orlachallengebackend.repository.ProjectsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.ZoneId;
import java.util.*;

/**
 * Controller responsável por gerenciar as requisições relacionadas a projetos.
 * Disponibiliza as APIs abaixo:
 * - GET /projects
 * - GET /projects/{id}
 * - POST /projects
 *
 * @author ethamorim
 */
@RestController
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsRepository repository;
    private final EmployeesRepository employeesRepository;
    private final EmployeesProjectsRepository employeesProjectsRepository;
    public ProjectsController(
            ProjectsRepository repository,
            EmployeesRepository employeesRepository,
            EmployeesProjectsRepository employeesProjectsRepository
    ) {
        this.repository = repository;
        this.employeesRepository = employeesRepository;
        this.employeesProjectsRepository = employeesProjectsRepository;
    }

    @GetMapping
    public List<ProjectModel> getProjects() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProjectModel getProject(@PathVariable Integer id) {
        var project = repository.findById(id)
                .orElseThrow(() -> new NoRecordFoundException("There is no project with given id"));
        project.setEmployees(repository.findMembersByProjectId(project.getId()));
        return project;
    }

    @PostMapping
    public ResponseEntity<ProjectModel> postProject(@RequestBody Project project) {
        EmployeeModel owner;
        if (project.ownerEmail() != null) {
            var optionalOwner = employeesRepository.findByEmail(project.ownerEmail());
            owner = optionalOwner.orElseThrow(() -> new NoRecordFoundException("There is no employee with given ownerEmail"));
        } else {
            var optionalOwner = employeesRepository.findById(project.ownerId());
            owner = optionalOwner.orElseThrow(() -> new NoRecordFoundException("There is no employee with given ownerId"));
        }

        Set<EmployeeModel> membersOfProject = new HashSet<>();
        membersOfProject.add(owner);
        if (project.members() != null) {
            project.members().forEach(email -> {
                var member = employeesRepository.findByEmail(email)
                        .orElseThrow(() -> new NoRecordFoundException("Project member does not exist"));
                membersOfProject.add(member);
            });
        }
        var newProject = new ProjectModel(
                project.name(),
                project.description(),
                project.creationDate().atStartOfDay(ZoneId.systemDefault()).toInstant(),
                project.previsionDate().atStartOfDay(ZoneId.systemDefault()).toInstant(),
                membersOfProject
        );
        var projectRecorded = repository.save(newProject);

        membersOfProject.forEach(member -> employeesProjectsRepository
                .save(new EmployeesProjectsModel(
                    member.equals(owner),
                    member,
                    newProject
                ))
        );
        projectRecorded.setEmployees(membersOfProject);
        return new ResponseEntity<>(projectRecorded, HttpStatus.CREATED);
    }
}
