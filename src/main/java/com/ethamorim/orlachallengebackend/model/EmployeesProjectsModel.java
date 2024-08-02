package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class EmployeesProjectsModel {

    public EmployeesProjectsModel() {}

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
