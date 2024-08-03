package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "employees_projects")
public class EmployeesProjectsModel {

    public EmployeesProjectsModel() {}

    public EmployeesProjectsModel(boolean isOwner, EmployeeModel employee, ProjectModel project) {
        this.isOwner = isOwner;
        this.employee = employee;
        this.project = project;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    boolean isOwner;

    @NaturalId
    @ManyToOne
    EmployeeModel employee;

    @NaturalId
    @ManyToOne
    ProjectModel project;

    public int getId() {
        return id;
    }
}
