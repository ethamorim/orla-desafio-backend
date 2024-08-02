package com.ethamorim.orlachallengebackend;

import com.ethamorim.orlachallengebackend.model.DepartmentModel;
import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import com.ethamorim.orlachallengebackend.repository.DepartmentRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeeRepository;
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

    @Bean
    public CommandLineRunner populateDatabase(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository
    ) {
        return (args) -> {
            var existent = departmentRepository.findByEmail("it@test.com");
            if (existent == null) {
                System.out.println("Populating Database...");

                var department = new DepartmentModel();
                department.setName("IT");
                department.setEmail("it@test.com");
                departmentRepository.save(department);

                var employee = new EmployeeModel();
                employee.setCpf("12345678912");
                employee.setName("Orlando");
                employee.setEmail("orlando@test.com");
                employee.setIncome(12000L);
                employee.setManager(true);
                employee.setStartDate(LocalDate.now());
                employee.setDepartment(department);
                employeeRepository.save(employee);

                System.out.println("Database populated!");
            }
        };
    }
}
