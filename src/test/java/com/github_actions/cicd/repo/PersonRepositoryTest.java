package com.github_actions.cicd.repo;

import com.github_actions.cicd.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void shouldSaveAndFindPersonById() {
        // Arrange
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        person.setEmail("john.doe@example.com");

        // Act
        Person savedPerson = personRepository.save(person);
        Optional<Person> retrievedPerson = personRepository.findById(savedPerson.getId());

        // Assert
        assertThat(retrievedPerson).isPresent();
        assertThat(retrievedPerson.get().getName()).isEqualTo("John Doe");
    }
}
