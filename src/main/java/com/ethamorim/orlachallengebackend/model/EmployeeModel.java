package com.ethamorim.orlachallengebackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class EmployeeModel {

    public EmployeeModel() {}

    public EmployeeModel(String name, String email, String cpf, LocalDate startDate, long income, boolean isManager, DepartmentModel department) {
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

    @Column(nullable = false)
    String name;

    @NaturalId
    String email;

    @Column(nullable = false, unique = true)
    String cpf;

    @Column(nullable = false)
    LocalDate startDate;

    @Column(nullable = false)
    long income;

    boolean isManager;

    @ManyToOne
    DepartmentModel department;

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

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeModel that = (EmployeeModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email);
    }
}
