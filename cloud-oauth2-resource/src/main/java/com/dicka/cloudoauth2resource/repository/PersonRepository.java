package com.dicka.cloudoauth2resource.repository;

import com.dicka.cloudoauth2resource.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>{

    List<Person> findPersonByEmailAndUsername(String email, String username);
}
