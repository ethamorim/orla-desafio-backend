package com.ethamorim.orlachallengebackend.controller.resource;

/**
 * Record que representa um corpo de requisição de um departamento.
 * @param name Nome do departamento
 * @param email Email do departamento
 *
 * @author ethamorim
 */
public record Department(String name, String email) { }
