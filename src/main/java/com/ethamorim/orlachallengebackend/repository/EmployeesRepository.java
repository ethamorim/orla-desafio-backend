package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<EmployeeModel, Integer> {
    Optional<EmployeeModel> findByEmail(String email);
}
