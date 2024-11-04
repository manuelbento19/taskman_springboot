package com.bentoo.taskman.repositories;

import com.bentoo.taskman.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
