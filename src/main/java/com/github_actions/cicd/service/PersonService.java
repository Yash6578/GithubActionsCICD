package com.github_actions.cicd.service;

import com.github_actions.cicd.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    Person getPersonById(Long id);

    Person createPerson(Person person);

    Person updatePerson(Long id, Person updatedPerson);

    void deletePerson(Long id);
}
