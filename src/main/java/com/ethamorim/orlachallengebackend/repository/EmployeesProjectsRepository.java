package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.EmployeesProjectsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesProjectsRepository extends JpaRepository<EmployeesProjectsModel, Integer> { }
