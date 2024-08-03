package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório do recurso de Departamentos, responsável por abstrair
 * as operações com o banco de dados.
 *
 * @author ethamorim
 */
public interface DepartmentsRepository extends JpaRepository<DepartmentModel, Integer> {
    Optional<DepartmentModel> findByEmail(String email);
}
