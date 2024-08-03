package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.time.Instant;
import java.util.Set;

/**
 * Entidade que representa os projetos.
 * Possui uma relação de muitos para muitos com `EmployeeModel`.
 *
 * @author ethamorim
 */
@Entity
@Table(name = "projects")
public class ProjectModel {

    public ProjectModel() {}

    public ProjectModel(String name, String description, Instant creationDate, Instant previsionDate, Set<EmployeeModel> employees) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.previsionDate = previsionDate;
        this.employees = employees;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NaturalId
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    Instant creationDate;

    @Column(nullable = false)
    Instant previsionDate;

    @ManyToMany(mappedBy = "projects")
    Set<EmployeeModel> employees;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getPrevisionDate() {
        return previsionDate;
    }

    public void setPrevisionDate(Instant previsionDate) {
        this.previsionDate = previsionDate;
    }

    public Set<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeModel> employees) {
        this.employees = employees;
    }
}
