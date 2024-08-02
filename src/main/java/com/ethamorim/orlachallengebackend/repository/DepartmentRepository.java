package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByEmail(String email);
}
