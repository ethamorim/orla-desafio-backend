package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.time.Instant;

@Entity
public class ProjectModel {

    public ProjectModel() {}

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
}
