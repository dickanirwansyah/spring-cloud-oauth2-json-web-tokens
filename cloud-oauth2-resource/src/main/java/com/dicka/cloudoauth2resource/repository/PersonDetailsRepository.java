package com.dicka.cloudoauth2resource.repository;

import com.dicka.cloudoauth2resource.entity.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Long> {
}
