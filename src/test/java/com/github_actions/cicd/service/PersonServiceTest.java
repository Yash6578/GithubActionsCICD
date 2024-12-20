package com.github_actions.cicd.service;

import com.github_actions.cicd.entity.Person;
import com.github_actions.cicd.repo.PersonRepository;
import com.github_actions.cicd.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllPersons() {
        // Arrange
        Person person1 = new Person();
        person1.setName("John Doe");
        Person person2 = new Person();
        person2.setName("Jane Doe");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        // Act
        List<Person> persons = personService.getAllPersons();

        // Assert
        assertEquals(2, persons.size());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnPersonById() {
        // Arrange
        Person person = new Person();
        person.setId(1L);
        person.setName("John Doe");

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        // Act
        Person result = personService.getPersonById(1L);

        // Assert
        assertEquals("John Doe", result.getName());
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void shouldSavePerson() {
        // Arrange
        Person person = new Person();
        person.setName("John Doe");

        when(personRepository.save(person)).thenReturn(person);

        // Act
        Person result = personService.createPerson(person);

        // Assert
        assertEquals("John Doe", result.getName());
        verify(personRepository, times(1)).save(person);
    }
}
