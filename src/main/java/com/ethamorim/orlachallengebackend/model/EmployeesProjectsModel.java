package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

/**
 * Entidade que representa a associação entre Funcionários e Projetos.
 *
 * @author ethamorim
 */
@Entity
@Table(name = "employees_projects")
public class EmployeesProjectsModel {

    public EmployeesProjectsModel() {}

    public EmployeesProjectsModel(boolean isOwner, EmployeeModel employees, ProjectModel projects) {
        this.isOwner = isOwner;
        this.employees = employees;
        this.projects = projects;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    boolean isOwner;

    @NaturalId
    @ManyToOne
    EmployeeModel employees;

    @NaturalId
    @ManyToOne
    ProjectModel projects;

    public int getId() {
        return id;
    }
}
