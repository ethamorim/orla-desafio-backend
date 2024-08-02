package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByEmail(String email);
}
