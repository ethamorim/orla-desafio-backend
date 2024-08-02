package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> { }
