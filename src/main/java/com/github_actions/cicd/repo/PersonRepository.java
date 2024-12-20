package com.github_actions.cicd.repo;

import com.github_actions.cicd.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
