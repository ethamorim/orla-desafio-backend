package com.ethamorim.orlachallengebackend;

import com.ethamorim.orlachallengebackend.model.DepartmentModel;
import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import com.ethamorim.orlachallengebackend.repository.DepartmentsRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class OrlaChallengeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrlaChallengeBackendApplication.class, args);
    }

}
