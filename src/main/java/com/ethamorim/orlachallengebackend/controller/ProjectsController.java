package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.controller.resource.Project;
import com.ethamorim.orlachallengebackend.exception.NoRecordFoundException;
import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import com.ethamorim.orlachallengebackend.model.EmployeesProjectsModel;
import com.ethamorim.orlachallengebackend.model.ProjectModel;
import com.ethamorim.orlachallengebackend.repository.EmployeesProjectsRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeesRepository;
import com.ethamorim.orlachallengebackend.repository.ProjectsRepository;
import org.springframework.web.bind.annotation.*;
import java.time.ZoneId;
import java.util.*;

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
    public void postProject(@RequestBody Project project) {
        Optional<EmployeeModel> optionalOwner;
        if (project.ownerEmail() != null) {
            optionalOwner = employeesRepository.findByEmail(project.ownerEmail());
        } else {
            optionalOwner = employeesRepository.findById(project.ownerId());
        }
        var owner = optionalOwner.orElseThrow(() -> new NoRecordFoundException("There is no employee with given ownerId"));

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
        repository.save(newProject);

        membersOfProject.forEach(member -> employeesProjectsRepository
                .save(new EmployeesProjectsModel(
                    member.equals(owner),
                    member,
                    newProject
                ))
        );
    }
}
