package com.dicka.cloudoauth2jwt.repository;

import com.dicka.cloudoauth2jwt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long>{

    Users findByUsername(String username);
}
