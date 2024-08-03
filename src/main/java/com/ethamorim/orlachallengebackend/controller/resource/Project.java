package com.ethamorim.orlachallengebackend.controller.resource;

import java.time.LocalDate;
import java.util.Set;

public record Project(
        String name,
        String description,
        LocalDate creationDate,
        LocalDate previsionDate,
        String ownerEmail,
        int ownerId,
        Set<String> members
) { }
