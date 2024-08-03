package com.ethamorim.orlachallengebackend;

import com.ethamorim.orlachallengebackend.controller.EmployeesController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrlaChallengeBackendApplicationTests {

    @Autowired
    private EmployeesController controller;

    @Test
    void contextLoads() {
        Assertions.assertThat(controller).isNotNull();
    }

}
