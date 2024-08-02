package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer> { }
