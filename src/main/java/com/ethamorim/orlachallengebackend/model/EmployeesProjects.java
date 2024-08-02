package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class EmployeesProjects {

    public EmployeesProjects() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    boolean isOwner;

    @NaturalId
    @ManyToOne
    Employee employee;

    @NaturalId
    @ManyToOne
    Project project;

    public int getId() {
        return id;
    }
}
