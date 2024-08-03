package com.ethamorim.orlachallengebackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Teste de integração com as APIs criadas.
 * Testa as APIs de maneira gradual dados os recursos disponíveis.
 *
 * @author ethamorim
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProjectIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldPerformOperations() throws Exception {
        getAllEmployees()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0))
                .andDo(print());

        // Save a department
        postDepartment("IT", "it@test.com")
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());

        this.mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        // Should fail on non existent department
        postEmployee("Orlando", "orlando@test.com", "12345678921", "2021-01-01", 12000, true, 2)
                .andExpect(status().is4xxClientError())
                .andDo(print());

        // Retry operation on resource
        postEmployee("Orlando", "orlando@test.com", "12345678921", "2021-01-01", 12000, true, 1)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Orlando"))
                .andExpect(jsonPath("$.email").value("orlando@test.com"))
                .andDo(print());

        // Checks integrity constraint
        postEmployee("Orlando", "orlando@test.com", "12345678921", "2021-01-01", 12000, true, 1)
                .andExpect(status().is4xxClientError())
                .andDo(print());

        // Save some other employees
        postEmployee("Notmando", "notmando@test.com", "98323221234", "2022-01-01", 15000, false, 1)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Notmando"))
                .andExpect(jsonPath("$.email").value("notmando@test.com"))
                .andDo(print());

        postEmployee("Carlos", "carlos@test.com", "293948243421", "2022-02-07", 1000, false, 1)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Carlos"))
                .andExpect(jsonPath("$.email").value("carlos@test.com"))
                .andDo(print());

        // Verify new value of employees
        getAllEmployees()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andDo(print());

        // Check no projects are present
        getAllProjects()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0))
                .andDo(print());

        // Post of resource should fail on invalid owner email
        postProject("Papelzinho", "Secret Santa draw", LocalDate.now(), LocalDate.now().plusYears(2), "orla@test.com", 0, Set.of())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("There is no employee with given ownerEmail"))
                .andDo(print());

        // Post of resource should fail on invalid owner id
        postProject("Papelzinho", "Secret Santa draw", LocalDate.now(), LocalDate.now().plusYears(2), null, 34, Set.of())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("There is no employee with given ownerId"))
                .andDo(print());

        // Post of resource should fail on invalid members
        postProject("Papelzinho", "Secret Santa draw", LocalDate.now(), LocalDate.now().plusYears(2), "orlando@test.com", 0, Set.of("normand@test.com"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("Project member does not exist"))
                .andDo(print());

        // Create project resource successfully
        postProject("Papelzinho", "Secret Santa draw", LocalDate.now(), LocalDate.now().plusYears(2), "orlando@test.com", 0, Set.of("notmando@test.com"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Papelzinho"))
                .andExpect(jsonPath("$.description").value("Secret Santa draw"))
                .andExpect(jsonPath("$.employees.length()").value(2))
                .andDo(print());

        // Create another project with different members (just the owner)
        postProject("Vamoo", "Manage and administer racket sports championship", LocalDate.now(), LocalDate.now().plusYears(2), "carlos@test.com", 0, Set.of())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Vamoo"))
                .andExpect(jsonPath("$.description").value("Manage and administer racket sports championship"))
                .andExpect(jsonPath("$.employees.length()").value(1))
                .andDo(print());

        // Get updated resources of project
        getAllProjects()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());

        // Get specific project resource with its respective members
        this.mockMvc.perform(get("/projects/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Papelzinho"))
                .andExpect(jsonPath("$.employees.length()").value(2))
                .andExpect(jsonPath("$.employees[0].email").value("notmando@test.com"))
                .andExpect(jsonPath("$.employees[1].email").value("orlando@test.com"))
                .andDo(print());

        this.mockMvc.perform(get("/projects/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Vamoo"))
                .andExpect(jsonPath("$.employees.length()").value(1))
                .andExpect(jsonPath("$.employees[0].email").value("carlos@test.com"))
                .andDo(print());
    }

    private ResultActions getAllEmployees() throws Exception {
        return this.mockMvc.perform(get("/employees"));
    }

    private ResultActions postEmployee(
            String name,
            String email,
            String cpf,
            String startDate,
            int income,
            boolean isManager,
            int departmentId
    ) throws Exception {
        return this.mockMvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{" +
                                "\"name\": \"" + name + "\", " +
                                "\"email\": \"" + email + "\", " +
                                "\"cpf\": \"" + cpf + "\", " +
                                "\"startDate\": \"" + startDate + "\", " +
                                "\"income\": " + income + ", " +
                                "\"isManager\": " + isManager + ", " +
                                "\"departmentId\": " + departmentId +
                                "}"
                        )
            );
    }


    private ResultActions postDepartment(
            String name,
            String email
    ) throws Exception {
        return this.mockMvc.perform(
                post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{" +
                                "\"name\": \"" + name + "\", " +
                                "\"email\": \"" + email + "\"" +
                                "}"
                        )
            );
    }

    private ResultActions getAllProjects() throws Exception {
        return this.mockMvc.perform(get("/projects"));
    }

    private ResultActions postProject(
            String name,
            String description,
            LocalDate creationDate,
            LocalDate previsionDate,
            String ownerEmail,
            int ownerId,
            Set<String> members
    ) throws Exception {
        var email = ownerEmail != null ? "\"" + ownerEmail + "\"" : null;
        return this.mockMvc.perform(
                post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{" +
                                "\"name\": \"" + name + "\", " +
                                "\"description\": \"" + description + "\", " +
                                "\"creationDate\": \"" + creationDate + "\", " +
                                "\"previsionDate\": \"" + previsionDate + "\", " +
                                "\"ownerEmail\": " + email + ", " +
                                "\"ownerId,\": " + ownerId + ", " +
                                "\"members\": " + members.stream()
                                        .map(m -> "\"" + m + "\"")
                                        .collect(Collectors.toSet()) +
                                "}"
                        )
            );
    }
}
