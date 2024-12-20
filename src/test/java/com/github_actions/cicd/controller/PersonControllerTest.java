package com.github_actions.cicd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github_actions.cicd.entity.Person;
import com.github_actions.cicd.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllPersons() throws Exception {
        // Arrange
        Person person1 = new Person();
        person1.setName("John Doe");
        Person person2 = new Person();
        person2.setName("Jane Doe");

        when(personService.getAllPersons()).thenReturn(Arrays.asList(person1, person2));

        // Act & Assert
        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void shouldCreatePerson() throws Exception {
        // Arrange
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        person.setEmail("john.doe@example.com");

        when(personService.createPerson(any(Person.class))).thenReturn(person);

        // Act & Assert
        mockMvc.perform(post("/api/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void shouldDeletePerson() throws Exception {
        // Arrange
        doNothing().when(personService).deletePerson(1L);

        // Act & Assert
        mockMvc.perform(delete("/api/persons/1"))
                .andExpect(status().isOk());
        verify(personService, times(1)).deletePerson(1L);
    }
}
