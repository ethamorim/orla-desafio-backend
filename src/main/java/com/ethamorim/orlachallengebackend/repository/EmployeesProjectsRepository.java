package com.ethamorim.orlachallengebackend.repository;

import com.ethamorim.orlachallengebackend.model.EmployeesProjectsModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório da entidade da associação Funcionários-Projetos, responsável por abstrair
 * as operações com o banco de dados.
 *
 * @author ethamorim
 */
public interface EmployeesProjectsRepository extends JpaRepository<EmployeesProjectsModel, Integer> { }
