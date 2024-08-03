package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.model.DepartmentModel;
import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import com.ethamorim.orlachallengebackend.model.ProjectModel;
import com.ethamorim.orlachallengebackend.repository.EmployeesProjectsRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeesRepository;
import com.ethamorim.orlachallengebackend.repository.ProjectsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

/**
 * Testes unitários do controller `ProjectsController`.
 *
 * @author ethamorim
 */
@WebMvcTest(ProjectsController.class)
public class ProjectsControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    ProjectsRepository projectsRepository;
    @MockBean
    EmployeesRepository employeesRepository;
    @MockBean
    EmployeesProjectsRepository employeesProjectsRepository;

    @Test
    public void shouldFindAllProjects() throws Exception {
        Mockito.when(projectsRepository.findAll()).thenReturn(List.of(new ProjectModel(), new ProjectModel(), new ProjectModel()));
        mvc.perform(MockMvcRequestBuilders.get("/projects"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void shouldNotFindSingleProject() throws Exception {
        Mockito.when(projectsRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/projects/1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("There is no project with given id"));
    }

    @Test
    public void shouldFindSingleProject() throws Exception {
        Mockito.when(projectsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new ProjectModel()));
        mvc.perform(MockMvcRequestBuilders.get("/projects/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotFindProjectOwner() throws Exception {
        Mockito.when(employeesRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ownerEmail\": \"email@test.com\"}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("There is no employee with given ownerEmail"));

        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ownerId\": 1}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("There is no employee with given ownerId"));
    }

    @Test
    public void shouldNotFindProjectMembers() throws Exception {
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new EmployeeModel()));
        Mockito.when(employeesRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ownerId\": 1, \"members\": [\"orlando@test.com\"]}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Project member does not exist"));
    }

    @Test
    public void shouldSaveProject() throws Exception {
        Mockito.when(employeesRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(new EmployeeModel()));
        Mockito.when(employeesRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new EmployeeModel()));
        Mockito.when(projectsRepository.save(Mockito.any(ProjectModel.class))).thenReturn(new ProjectModel());
        mvc.perform(MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"creationDate\": \"2024-08-03\", \"previsionDate\": \"2024-08-03\",  \"ownerId\": 1, \"members\": [\"orlando@test.com\"]}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees.length()").value(1));
    }
}
