package ru.netology.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.netology.authservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
