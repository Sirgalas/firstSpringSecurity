package ru.sergalas.FirstSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergalas.FirstSecurity.entities.users.entities.User;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
