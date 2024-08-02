package com.ethamorim.orlachallengebackend.controller.resource;

import java.time.LocalDate;

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
