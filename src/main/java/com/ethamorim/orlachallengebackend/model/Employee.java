package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;

@Entity
public class Employee {

    public Employee() {}

    public Employee(String name, String email, String cpf, LocalDate startDate, long income, boolean isManager, Department department) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.startDate = startDate;
        this.income = income;
        this.isManager = isManager;
        this.department = department;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @NaturalId
    String email;

    String cpf;

    LocalDate startDate;

    long income;

    boolean isManager;

    @ManyToOne
    Department department;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
