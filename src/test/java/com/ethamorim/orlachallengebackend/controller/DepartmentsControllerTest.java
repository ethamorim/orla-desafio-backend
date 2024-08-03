package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.model.DepartmentModel;
import com.ethamorim.orlachallengebackend.repository.DepartmentsRepository;
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

/**
 * Testes unitários do controller `DepartmentsController`.
 *
 * @author ethamorim
 */
@WebMvcTest(DepartmentsController.class)
public class DepartmentsControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    DepartmentsRepository repository;

    @Test
    public void shouldFindAllEmployees() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(List.of(new DepartmentModel()));
        mvc.perform(MockMvcRequestBuilders.get("/departments"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    public void shouldSaveEmployee() throws Exception {
        Mockito.when(repository.save(Mockito.any(DepartmentModel.class))).thenReturn(new DepartmentModel());
        mvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"IT\", \"email\": \"it@test.com\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
