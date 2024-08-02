package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class DepartmentModel {

    public DepartmentModel() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String name;

    @NaturalId
    String email;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}