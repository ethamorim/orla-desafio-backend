package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositório do recurso de Funcionários, responsável por abstrair
 * as operações com o banco de dados.
 *
 * @author ethamorim
 */
public interface EmployeesRepository extends JpaRepository<EmployeeModel, Integer> {
    Optional<EmployeeModel> findByEmail(String email);
}
