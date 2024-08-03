package com.ethamorim.orlachallengebackend.controller;

import com.ethamorim.orlachallengebackend.model.DepartmentModel;
import com.ethamorim.orlachallengebackend.model.EmployeeModel;
import com.ethamorim.orlachallengebackend.repository.DepartmentsRepository;
import com.ethamorim.orlachallengebackend.repository.EmployeesRepository;
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

@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    EmployeesRepository employeesRepository;
    @MockBean
    DepartmentsRepository departmentsRepository;

    @Test
    public void shouldFindAllEmployees() throws Exception {
        Mockito.when(employeesRepository.findAll()).thenReturn(List.of(new EmployeeModel(), new EmployeeModel()));
        mvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void shouldNotFindDepartment() throws Exception {
        Mockito.when(departmentsRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"departmentEmail\": \"email@test.com\"}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("There is no department with the given departmentEmail"));

        Mockito.when(departmentsRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"departmentId\": 1}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("There is no department with the given departmentId"));
    }

    @Test
    public void shouldSaveEmployee() throws Exception {
        Mockito.when(departmentsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new DepartmentModel()));
        Mockito.when(employeesRepository.save(Mockito.any(EmployeeModel.class))).thenReturn(new EmployeeModel());
        mvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Test\", \"departmentId\": 1 }"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
