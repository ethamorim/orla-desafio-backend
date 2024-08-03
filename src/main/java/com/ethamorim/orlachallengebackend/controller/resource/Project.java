package com.ethamorim.orlachallengebackend.controller.resource;

import java.time.LocalDate;
import java.util.Set;

/**
 * Record que representa um corpo de requisição de um projeto.
 * @param name Nome do projeto
 * @param description Descrição do projeto
 * @param creationDate Data de criação do projeto
 * @param previsionDate Data de previsão de término do projeto
 * @param ownerEmail Email do dono do projeto
 * @param ownerId Id do dono do projeto
 * @param members Lista de emails dos membros do projeto
 *
 * @author ethamorim
 */
public record Project(
        String name,
        String description,
        LocalDate creationDate,
        LocalDate previsionDate,
        String ownerEmail,
        int ownerId,
        Set<String> members
) { }
