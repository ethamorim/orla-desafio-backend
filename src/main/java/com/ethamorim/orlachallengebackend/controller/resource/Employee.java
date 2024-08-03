package com.ethamorim.orlachallengebackend.controller.resource;

import java.time.LocalDate;

/**
 * Record que representa um corpo de requisição de um funcionário.
 * @param name Nome do funcionário
 * @param cpf CPF do funcionário
 * @param email Email do funcionário
 * @param startDate Data de início do funcionário
 * @param income Salário do funcionário
 * @param isManager Indica se o funcionário é gerente ou não do seu departamento
 * @param departmentId Id do departamento do funcionário
 * @param departmentEmail Email do departamento do funcionário
 *
 * @author ethamorim
 */
public record Employee(
        String name,
        String cpf,
        String email,
        LocalDate startDate,
        long income,
        boolean isManager,
        int departmentId,
        String departmentEmail
) { }
